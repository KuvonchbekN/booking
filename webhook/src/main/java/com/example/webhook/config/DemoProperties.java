package com.example.webhook.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "spring.bot")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemoProperties {
    private String username;
    private String url;
    private String token;
    private String telegramUrl;

}
