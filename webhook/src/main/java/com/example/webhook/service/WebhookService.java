package com.example.webhook.service;


import com.example.webhook.BookingCache;
import com.example.webhook.constants.ResponseMessage;
import com.example.webhook.backend.DataTransfer;
import com.example.webhook.config.DemoProperties;
import com.example.webhook.enums.BookingType;
import com.example.webhook.enums.UserState;
import com.example.webhook.markups.InlineMarkup;
import com.example.webhook.markups.ReplyMarkup;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Log4j2
public class WebhookService {

    private final RestTemplate restTemplate;
    private final DemoProperties demoProperties;
    private final ReplyMarkup replyMarkup;
    private final InlineMarkup inlineMarkup;
    private final DataTransfer dataTransfer;


    Map<String, UserState> userState = new HashMap<>();
    Map<String, BookingCache> bookingDataCollector = new HashMap<>();

    public void handleMessageQuery(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();

        switch (text) {
            case ResponseMessage.START -> {
                SendMessage sendMessage = sendReplyMarkup(chatId, ResponseMessage.CHOOSE_OPTION, replyMarkup.afterStartClicked());
                dataTransfer.execute(sendMessage);
                userState.put(chatId, UserState.START);
            }
            case ResponseMessage.MAKE_BOOKING -> {
                SendMessage sendMessage = sendInlineMarkup(chatId, ResponseMessage.CITY_LIST, inlineMarkup.showList(dataTransfer.getCityList()));
                dataTransfer.execute(sendMessage);
                userState.put(chatId, UserState.MADE_BOOKING);
                bookingDataCollector.put(chatId, new BookingCache());
            }
        }
    }

    public void handleCallBackQuery(Update update) {
        String chatId = update.getCallbackQuery().getFrom().getId().toString();
        String data = update.getCallbackQuery().getData();


        UserState currentUserState = this.userState.get(chatId);
        if (currentUserState == UserState.MADE_BOOKING) {
            List<String> officeListByCityName = dataTransfer.getOfficeListByCityName(data);
            InlineKeyboardMarkup markup = inlineMarkup.showList(officeListByCityName);
            SendMessage sendMessage = sendInlineMarkup(chatId, ResponseMessage.OFFICE_LIST, markup);

            userState.put(chatId, UserState.CITY_SELECTED);
            dataTransfer.execute(sendMessage);

            BookingCache bookingCache = bookingDataCollector.get(chatId);
            bookingCache.setCityName(data);
            bookingDataCollector.put(chatId, bookingCache);
        }
        else if (currentUserState == UserState.CITY_SELECTED) {
            SendMessage sendMessage = sendInlineMarkup(chatId, ResponseMessage.BOOKING_TYPE_LIST, inlineMarkup.showBookingTypes());
            userState.put(chatId, UserState.OFFICE_SELECTED);
            dataTransfer.execute(sendMessage);

            BookingCache bookingCache = bookingDataCollector.get(chatId);
            bookingCache.setOfficeName(data);
            bookingDataCollector.put(chatId, bookingCache);
        }
        else if (currentUserState == UserState.OFFICE_SELECTED){
            SendMessage sendMessage = new SendMessage(chatId, "you chose " + data);
            userState.put(chatId, UserState.BOOK_TYPE_SELECTED);
            dataTransfer.execute(sendMessage);

            BookingCache bookingCache = bookingDataCollector.get(chatId);
            bookingCache.setBookingType(BookingType.valueOf(data));
            bookingDataCollector.put(chatId, bookingCache);
        }
    }

    private SendMessage sendInlineMarkup(String chatId, String text, InlineKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }

    private SendMessage sendReplyMarkup(String chatId, String text, ReplyKeyboardMarkup markup) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }
}