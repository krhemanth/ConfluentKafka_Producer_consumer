package com.fmac.loanpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LoanPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanPublisherApplication.class, args);
	}

}
