package org.mypr;



import javax.servlet.http.HttpSessionListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.mypr")
//@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public HttpSessionListener httpSessionListener(){
	    return new MyprListener();
	}
	
	@Bean
	public HttpSessionEventPublisher eventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
