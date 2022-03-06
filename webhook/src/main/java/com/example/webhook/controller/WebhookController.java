package com.example.webhook.controller;

import com.example.webhook.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;


@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
@Log4j2
public class WebhookController {


    private final TelegramService telegramService;

    @PostMapping
    public void getUpdates(@RequestBody Update update) {
        telegramService.getUpdate(update);
    }
}
