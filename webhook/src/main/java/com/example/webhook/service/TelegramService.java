package com.example.webhook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final WebhookService webhookService;

    public void getUpdate(Update update){
        if (update.hasMessage()){
            webhookService.handleMessageQuery(update);
        }else if (update.hasCallbackQuery()){
            webhookService.handleCallBackQuery(update);
        }
    }
}
