package com.study.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //AuditingEntityListener 활성화
public class MybatisJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisJpaApplication.class, args);
	}

}
