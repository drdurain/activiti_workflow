package com.wfm.wfm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class WfmApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfmApplication.class, args);
	}

    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/loan?useSSL=false")
                .username("root") //db user
                .password("") //enter your db password in the string
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }
}

