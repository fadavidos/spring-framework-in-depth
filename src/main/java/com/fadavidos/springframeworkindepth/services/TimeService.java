package com.fadavidos.springframeworkindepth.services;

import com.fadavidos.springframeworkindepth.aspect.MyCountable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {

    private static final DateTimeFormatter FORMATTER_24 = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_12 = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Value("#{ '${spring.profiles.active}' == 'dev' ? false : true}")
    private Boolean is24;

    public TimeService(){
        super();
    }

    @MyCountable
    public String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        if (is24) {
            return FORMATTER_24.format(now);
        }
        return FORMATTER_12.format(now);
    }

}
