package com.fadavidos.springframeworkindepth;

import com.fadavidos.springframeworkindepth.config.MyApplicationConfig;
import com.fadavidos.springframeworkindepth.services.OutputService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringFrameworkInDepthApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new AnnotationConfigApplicationContext(MyApplicationConfig.class);

		OutputService outputService = context.getBean(OutputService.class);
		for (int i=0;i<5;i++){
			outputService.generateOutput();
			Thread.sleep(5000);
		}
	}

}
