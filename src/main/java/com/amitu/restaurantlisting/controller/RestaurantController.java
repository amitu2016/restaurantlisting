package com.amitu.restaurantlisting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amitu.restaurantlisting.dto.RestaurantDto;
import com.amitu.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service){
        this.service = service;
    }

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        List<RestaurantDto> allRestaurantsList = service.fetchAllRestaurants();
        return new ResponseEntity<>(allRestaurantsList, HttpStatus.OK);
    }
    
    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDto> saveRestaurant(@RequestBody RestaurantDto restaurantDTO) {
        RestaurantDto restaurantAdded = service.addRestaurantInDB(restaurantDTO);
        return new ResponseEntity<>(restaurantAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<RestaurantDto> findRestaurantById(@PathVariable Integer id) {
       return service.fetchRestaurantById(id);
    }
    
    @PutMapping("/updateRestaurant")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto){
    	RestaurantDto restaurantUpdated = service.updateRestaurantInDb(restaurantDto);
    	 return new ResponseEntity<>(restaurantUpdated, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<RestaurantDto> deleteRestaurantById(@PathVariable Integer id) {
    	 RestaurantDto dto =  service.deleteById(id);
    	 return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    

}
