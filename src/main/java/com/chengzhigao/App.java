package com.chengzhigao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.chengzhigao.mapper")//mapper扫描
@EnableTransactionManagement
@EnableScheduling
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

