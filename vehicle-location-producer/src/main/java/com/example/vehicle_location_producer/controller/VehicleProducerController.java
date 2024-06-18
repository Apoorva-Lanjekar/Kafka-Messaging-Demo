package com.example.vehicle_location_producer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vehicle_location_producer.entity.VehicleLocation;
import com.example.vehicle_location_producer.service.VehicleLocationService;

@RestController
@RequestMapping("/producer")
public class VehicleProducerController {
	@Autowired
	private VehicleLocationService locationService;

	@GetMapping("/sendMessage")
	public String sendMessage() {
//		kafkaTemplate.send(topic1, "Hello Apoorva" );
		return "Message";
	}
	
	@PostMapping("/vehicle")
	public String sendVehicleUpdate(@RequestBody VehicleLocation location) {
		locationService.sendLocation(location);
		return "Vehicle sent successfuly";
	}

	@GetMapping("/vehicle/{id}")
	public VehicleLocation getVehicleByID(@PathVariable("id") Integer id) {
		VehicleLocation v = locationService.getVehicleByID(id);
		return v;
	}
	
	@GetMapping("/vehicle")
	public List<VehicleLocation> getAllVehicles() {
		List<VehicleLocation> v = locationService.getAllVehicles();
		return v;
	}
}
