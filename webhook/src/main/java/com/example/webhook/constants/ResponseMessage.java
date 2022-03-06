package com.example.webhook.constants;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface ResponseMessage {
    String CHOOSE_OPTION = "Please choose an option: ";



    //keyboard texts
    String MAKE_BOOKING = "Make a booking";
    String SEE_BOOK_LIST = "See my bookings";
    String START = "/start";
    String CITY_LIST= "Please choose a city: ";
    String OFFICE_LIST = "Please choose an office";
    String BOOKING_TYPE_LIST = "Please choose a booking type of your choice";
    String ONE_DAY_BOOKING = "One day booking";
    String CONTINUOUS_BOOKING = "Continuous booking";
    String RECURRING_BOOKING = "Recurring booking";

}
