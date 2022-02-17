package com.example.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.entity.Server;

public interface ServerRepo extends JpaRepository<Server, Long> {
	Server findByIpAddress(String IpAddress);

}
