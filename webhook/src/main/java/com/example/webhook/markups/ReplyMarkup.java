package com.example.webhook.markups;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyMarkup {

    public ReplyKeyboardMarkup afterStartClicked(){ //this keyboards after the start is typed
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();

        markup.setSelective(true);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("See my bookings");
        keyboardRow.add("Make a booking");
        keyboardRowList.add(keyboardRow);
        markup.setKeyboard(keyboardRowList);

        return markup;
    }
}
