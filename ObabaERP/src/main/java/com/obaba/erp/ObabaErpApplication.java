package com.obaba.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.obaba.erp.entities.TUser;
import com.obaba.erp.utils.FileStorageProperties;


@SpringBootApplication(scanBasePackages = { "com.obaba.erp", "com.obaba.erp.utils",
		"com.obaba.erp.controller", "com.obaba.erp.dao", "com.obaba.erp.daoImpl",
		 "com.obaba.erp.entities", "com.obaba.erp.service", "com.obaba.erp.serviceImpl" })
@ComponentScan(basePackages = "com.obaba.erp")
@EnableConfigurationProperties({
	FileStorageProperties.class
})

@EntityScan(basePackageClasses = { TUser.class })
@EnableJpaRepositories("com.obaba.erp")
public class ObabaErpApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ObabaErpApplication.class, args);
	}
	
}
