package com.example.restservice.controller;

import java.util.*;

import com.example.restservice.model.Greeting;
import com.example.restservice.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

	private static final String template = "Hello, %s!";
	private int counter;
	private final List<User> list = new ArrayList<>();
	private final Set<User> set = new HashSet<>();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter++, String.format(template, name));
	}

	@GetMapping("/user/list")
	public List<User> listPair(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email) {
		list.add(counter++, new User(java.util.UUID.randomUUID(), String.format(template, name), email));
		return list;
	}

	@GetMapping("/delete/list")
	public List<User> removeList(@RequestParam(value = "uuid") UUID uuid) {
		list.removeIf(obj -> obj.getId() == uuid);
		counter = list.size();
		return list;
	}

	@GetMapping("/user/set")
	public Set<User> setPair(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email) throws Exception {
		User test = new User(java.util.UUID.randomUUID(), name, email);

		for (User user : set) {
			if(user.emailCheck(test)) {
				throw new Exception("test");
			}
			if(user.idCheck(test)) {
				throw new Exception("test");
			}
		}

		set.add(new User(java.util.UUID.randomUUID(), String.format(template, name), email));
		return set;
	}

	@GetMapping("/delete/set")
	public Set<User> removeSet(@RequestParam(value = "uuid") UUID uuid) {
		set.removeIf(obj -> obj.getId().equals(uuid));
		return set;
	}
}

