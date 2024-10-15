package com.example.librarymanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy()
@EnableCaching
public class LibrarymanagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagmentApplication.class, args);
	}

}
