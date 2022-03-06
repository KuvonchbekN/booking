package com.example.webhook;

import com.example.webhook.enums.BookingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class BookingCache {
    private String cityName;
    private String officeName;
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;
    private Boolean hasParking;
}