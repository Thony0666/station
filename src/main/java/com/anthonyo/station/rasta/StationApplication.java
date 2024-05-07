package com.anthonyo.station.rasta;

import com.anthonyo.station.rasta.connection.Config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationApplication.class, args);
	}
    @Bean
	CommandLineRunner log (Config test){

		return args -> {
//			Config test = new Config("teste","Rskoro","boto");
			System.out.println(test.getUrl());
		};
	}
}
