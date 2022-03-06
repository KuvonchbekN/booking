package com.example.webhook.backend;

import com.example.webhook.config.DemoProperties;
import com.example.webhook.payload.CityResponseDto;
import com.example.webhook.payload.OfficeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTransfer {
    private final RestTemplate restTemplate;
    private final DemoProperties demoProperties;

    public void execute(SendMessage sendMessage) {
        restTemplate.postForObject(demoProperties.getTelegramUrl() + demoProperties.getToken() + "/sendMessage",
                sendMessage, Object.class);
    }

    public List<String> getCityList() {
        CityResponseDto cityList = restTemplate.getForObject("http://localhost:8123/office/cityList", CityResponseDto.class);
        assert cityList != null;
        return cityList.getCityList();
    }


    public List<String> getOfficeListByCityName(String cityName) {
        OfficeResponseDto officeResponse = restTemplate.getForObject("http://localhost:8123/office/officeList/" + cityName, OfficeResponseDto.class);
        assert officeResponse != null;
        return officeResponse.getOfficeList();
    }
}
