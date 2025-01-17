package com.cyfrifpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages = "com.cyfrifpro")
@OpenAPIDefinition(
		info = @Info(
				title = "GrandSpace Application",
		        version = "1.0.0",
		        description = "This application is mainly used to manage the construction sites"
		    ),
		servers = @Server(
				url = "http://localhost:9090",
				description = "This is the url by using we can access the clients and managers"
		    )
		)
public class GrandSpaceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrandSpaceProjectApplication.class, args);
	}

}
