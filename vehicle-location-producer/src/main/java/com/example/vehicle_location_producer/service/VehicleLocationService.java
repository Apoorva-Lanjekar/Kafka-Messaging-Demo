package com.example.vehicle_location_producer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.vehicle_location_producer.entity.VehicleLocation;
import com.example.vehicle_location_producer.repository.VehicleLocationRepository;


@Service
public class VehicleLocationService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    @Autowired
    private VehicleLocationRepository repository;

	private static final String topic_save = "vehicle-location";
	private static final String topic_get = "vehicle-location-id";
	private static final String topic_all = "vehicle-location-all";

    public boolean sendLocation(VehicleLocation location) {
    	repository.save(location);
        kafkaTemplate.send(topic_save, location);

        return true;
    }
    
    public VehicleLocation getVehicleByID(Integer id) {
    	Optional<VehicleLocation> v = repository.findById(id);
    	if (v.isPresent()) {
    		VehicleLocation vl = v.get();
    		kafkaTemplate.send(topic_get, vl);
    		return vl;
    	}

        return null;
    }
    
    public List<VehicleLocation> getAllVehicles() {
    	List<VehicleLocation> v = (List<VehicleLocation>) repository.findAll();
        kafkaTemplate.send(topic_all, v);

        return v;
    }
}