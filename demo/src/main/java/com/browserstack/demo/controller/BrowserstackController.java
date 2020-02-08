package com.browserstack.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.browserstack.demo.model.Greeting;
import com.browserstack.demo.model.HelloMessage;
import com.browserstack.demo.service.BrowserstackService;

@RestController
public class BrowserstackController {

	@Autowired
	BrowserstackService service;

	@GetMapping(value = "/read")
	public void readLastLines() throws Exception {
		service.readLastNLines();
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		String logs = service.readLastNLines();
		return new Greeting(logs);
	}

}
