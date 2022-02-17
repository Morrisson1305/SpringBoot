package com.example.server;

import com.example.server.entity.Server;
import com.example.server.enumeration.Status;
import com.example.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.server.enumeration.Status.SERVER_DOWN;
import static com.example.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return  args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Windows OS", "32 GB", "Personal Computer",
					"http://localhost:8080/server/image/server1", SERVER_UP));

			serverRepo.save(new Server(null, "192.168.1.141", "Kali Linux", "16 GB", "web server",
					"http://localhost:8080/server/image/server2", SERVER_UP));

			serverRepo.save(new Server(null, "192.168.1.62", "Fedora Linux", "32 GB", "Dell Tower",
					"http://localhost:8080/server/image/server3", SERVER_DOWN));

			serverRepo.save(new Server(null, "192.168.1.17", "Windows OS", "32 GB", "Personal Computer",
					"http://localhost:8080/server/image/server4", SERVER_UP));

		};
	}

}
