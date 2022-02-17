package com.example.server.controller;

import com.example.server.entity.Response;
import com.example.server.entity.Server;

import com.example.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static com.example.server.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("servers", serverService.list(30)))
                        .message("server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
         Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", server))
                        .message(server.getStatus() == SERVER_UP ? "Ping successful" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    //post request
    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverService.create(server)))
                        .message("Server has been created successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("server", serverService.get(id)))
                        .message("server Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted", serverService.delete(id)))
                        .message("server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.Bruno") + "/Downloads/servers/" + fileName));
    }
}
