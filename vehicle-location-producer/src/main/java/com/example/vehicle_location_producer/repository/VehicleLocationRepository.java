package com.example.vehicle_location_producer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.vehicle_location_producer.entity.VehicleLocation;

@Repository
public interface VehicleLocationRepository extends CrudRepository<VehicleLocation, Integer>{

}
