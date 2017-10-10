package com.harmontronics.lego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJms
@ServletComponentScan
public class LegoWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegoWechatApplication.class, args);
	}
}
