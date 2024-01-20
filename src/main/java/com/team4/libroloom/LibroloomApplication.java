package com.team4.libroloom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibroloomApplication {

	public String PGUSER=System.getenv("PGUSER");
	public String POSTGRES_PASSWORD=System.getenv("POSTGRES_PASSWORD");
	public String RAILWAY_TCP_PROXY_DOMAIN=System.getenv("RAILWAY_TCP_PROXY_DOMAIN");
	public String RAILWAY_TCP_PROXY_PORT=System.getenv("RAILWAY_TCP_PROXY_PORT");
	public String PGDATABASE=System.getenv("PGDATABASE");
	public static void main(String[] args) {
		SpringApplication.run(LibroloomApplication.class, args);
	}

}
