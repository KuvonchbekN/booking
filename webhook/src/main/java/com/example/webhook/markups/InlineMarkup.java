package com.example.webhook.markups;

import com.example.webhook.constants.ResponseMessage;
import com.example.webhook.enums.BookingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class InlineMarkup {

    public InlineKeyboardMarkup showList(List<String> itemList) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> mainList = new ArrayList<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        for (String city : itemList) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(city);
            button.setCallbackData(city);
            buttonList.add(button);
            if (buttonList.size() == 3) {
                mainList.add(buttonList);
                buttonList = new ArrayList<>();
            }
        }

        if (buttonList.size() != 0) {
            mainList.add(buttonList);
        }

        markup.setKeyboard(mainList);
        return markup;
    }

    public InlineKeyboardMarkup showBookingTypes() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> mainList = new ArrayList<>();

        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton(BookingType.ONE_DAY.name());
        button.setCallbackData(BookingType.ONE_DAY.name());
        buttonList.add(button);
        mainList.add(buttonList);


        List<InlineKeyboardButton> buttonList2 = new ArrayList<>();
        InlineKeyboardButton button2 = new InlineKeyboardButton(BookingType.CONTINUOUS.name());
        button2.setCallbackData(BookingType.CONTINUOUS.name());
        buttonList2.add(button2);
        mainList.add(buttonList2);

        List<InlineKeyboardButton> buttonList3 = new ArrayList<>();
        InlineKeyboardButton button3 = new InlineKeyboardButton(BookingType.RECURRING.name());
        button3.setCallbackData(BookingType.RECURRING.name());
        buttonList3.add(button3);
        mainList.add(buttonList3);

        markup.setKeyboard(mainList);
        return markup;
    }





}
