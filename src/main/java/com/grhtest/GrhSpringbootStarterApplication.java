package com.grhtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author grh95
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.grhtest.mapper"})
@ComponentScan(basePackages = {"com.grhtest","org.n3r.idworker"})
public class GrhSpringbootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrhSpringbootStarterApplication.class, args);
	}

}
