package com.grhtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author grh95
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.grhtest.mapper"})
@ComponentScan(basePackages = {"com.grhtest","org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
@EnableAsync
public class GrhSpringbootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrhSpringbootStarterApplication.class, args);
	}

}
