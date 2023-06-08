package com.fadavidos.springframeworkindepth.config;

import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.fadavidos.springframeworkindepth")
@EnableAspectJAutoProxy
public class MyApplicationConfig {

}
