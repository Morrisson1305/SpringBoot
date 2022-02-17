package com.example.server.entity;

import com.example.server.enumeration.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "server")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	
	@Id
	@GeneratedValue(
			 strategy = GenerationType.SEQUENCE,
		        generator = "server_sequence"
			
			)
	private Long id;
	@Column(unique = true)
	@NotEmpty(message = "IP address is required")
	private String ipAddress;
	 
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;

    public void setStatus(String s) {
    }
}
