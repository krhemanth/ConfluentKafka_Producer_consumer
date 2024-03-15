package com.fmac.loansubscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LoanSubscriberApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanSubscriberApplication.class, args);
	}

}
