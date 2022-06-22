package com.webPlusJava.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class WebBlogApplication {

	public static void main(String[] args) {
		Logger.getLogger("MyLogger").log(Level.WARNING,"hello MeLogger");
		SpringApplication.run(WebBlogApplication.class, args);
	}

}
