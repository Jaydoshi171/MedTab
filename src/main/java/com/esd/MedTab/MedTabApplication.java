package com.esd.MedTab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan({"com.esd.MedTab.controller","com.esd.MedTab.pojo","com.esd.MedTab.dao","com.esd.MedTab.util"})
public class MedTabApplication extends SpringBootServletInitializer implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MedTabApplication.class, args);
	}

	@Override
	 public void addViewControllers( ViewControllerRegistry registry) {
	  registry.addViewController("/").setViewName("welcome");
	  
	 }
}
