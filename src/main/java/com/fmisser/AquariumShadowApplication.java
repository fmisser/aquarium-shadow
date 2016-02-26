package com.fmisser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class AquariumShadowApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = null;
		try {
			configurableApplicationContext = SpringApplication.run(AquariumShadowApplication.class, args);
		} finally {
			if (configurableApplicationContext != null) {
				configurableApplicationContext.close();
			}
		}
	}
}
