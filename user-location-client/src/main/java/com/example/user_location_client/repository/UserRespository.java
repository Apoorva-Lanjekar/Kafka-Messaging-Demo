package com.example.user_location_client.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.user_location_client.entity.UserLocation;

public interface UserRespository extends CrudRepository<UserLocation, Integer>{

}
