package com.example.user_location_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_location_client.entity.UserLocation;
import com.example.user_location_client.repository.UserRespository;
import com.example.user_location_client.service.UserLocationService;

@RestController
@RequestMapping("/consumer")
public class UserController {
	@Autowired
	UserRespository userRespository;
	
	@Autowired
	UserLocationService locationService;
	
	@GetMapping("/test")
	public String getTest() {
		return "Test Data";
	}
	
	@PostMapping("/user")
	public UserLocation saveUser(@RequestBody UserLocation user) {
		userRespository.save(user);
		return user;
	}
	
	@PostMapping("/book")
	public UserLocation bookCab(@RequestBody UserLocation user) {
		UserLocation userLocation= userRespository.findById(user.getId);
		return userLocation;
	}

}
