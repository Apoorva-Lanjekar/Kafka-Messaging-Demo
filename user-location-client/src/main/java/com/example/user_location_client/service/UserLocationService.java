package com.example.user_location_client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.user_location_client.entity.UserLocation;
import com.example.user_location_client.entity.VehicleLocation;

@Service
public class UserLocationService {
    @Autowired
	UserRespository userRespository;
	List<String> messages = new ArrayList();
	
    @KafkaListener(topics = "vehicle-location", groupId = "location_group", containerFactory = "vehicleListener")
    public void newCabLocation(VehicleLocation location) {
        UserLocation user = userRespository.findById(1);
    	if(user) {
            if(Math.round(user.getLat()) == Math.round(location.getLat()) 
            && Math.round(user.getLongitude()) == Math.round(location.getLongitude())) {
                user.setCabBooked(location.getVehicleNumber());
                userRespository.save(user);
            }
        }
        System.out.println("Consumer received ...." + location);
    }
    
    @KafkaListener(topics = "vehicle-location-id", groupId = "location_group")
    public void getCabLocation(String location) {
        System.out.println("Consumer received ...." + location);
    }
    
    @KafkaListener(topics = "vehicle-location-all", groupId = "location_group")
    public void getAllCabs(String location) {
        System.out.println("Consumer received ...." + location);
    }
    

    @KafkaListener(topics = "userMessage")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        System.out.println("message = " + data);
        return messages;
    }
}