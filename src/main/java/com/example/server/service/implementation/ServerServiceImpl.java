package com.example.server.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.server.entity.Server;
import com.example.server.repo.ServerRepo;
import com.example.server.service.ServerService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class ServerServiceImpl implements ServerService{
	
	private final ServerRepo serverRepo;
	

	private static final String SERVER_DOWN = null;
	private static final String SERVER_UP = null;

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}
	@Override
	public  Server ping(String ipAddress) throws IOException {
		log.info("pinging server ip:{}", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
		serverRepo.save(server);
		return serverRepo.save(server);
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepo.findAll(PageRequest.of(0,limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("fetching server by is: {}", id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server {}", server.getName());
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Updating server {}", id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;
	}
	
	private String setServerImageUrl() {
		String[] imageNames = {"server1.png", "server3.png", "server3.png", "server4.png"};
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
	} 

}
