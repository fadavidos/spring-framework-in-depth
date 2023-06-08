package com.fadavidos.springframeworkindepth.services;

import com.fadavidos.springframeworkindepth.aspect.MyCountable;
import com.fadavidos.springframeworkindepth.aspect.MyLoggable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${app.greeting}")
    private String greeting;

    public GreetingService(){
        super();
    }

    @MyLoggable
    @MyCountable
    public String getGreeting(String name){
        return greeting + " " + name;
    }
}
