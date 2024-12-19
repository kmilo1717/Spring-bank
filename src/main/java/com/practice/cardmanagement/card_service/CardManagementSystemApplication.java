package com.practice.cardmanagement.card_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CardManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardManagementSystemApplication.class, args);
	}

}

@RestController
class StatusController {
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getStatus() {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "1");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
