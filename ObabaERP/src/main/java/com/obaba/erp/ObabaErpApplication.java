package com.obaba.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.obaba.erp.entities.UserAuth;


@SpringBootApplication(scanBasePackages = { "com.obaba.erp", "com.obaba.erp.utils",
		"com.obaba.erp.controller", "com.obaba.erp.dao", "com.obaba.erp.daoImpl",
		 "com.obaba.erp.model", "com.obaba.erp.service", "com.obaba.erp.serviceImpl" })
@ComponentScan(basePackages = "com.obaba.erp")

@EntityScan(basePackageClasses = { UserAuth.class })
@EnableJpaRepositories("com.obaba.erp")
public class ObabaErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObabaErpApplication.class, args);
	}
	
}
