package com.amitu.restaurantlisting.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amitu.restaurantlisting.dto.RestaurantDto;
import com.amitu.restaurantlisting.entity.Restaurant;
import com.amitu.restaurantlisting.mapper.RestaurantMapper;
import com.amitu.restaurantlisting.repository.RestaurantRepo;

@Service
public class RestaurantService {

    private final RestaurantRepo repo;

    public RestaurantService(RestaurantRepo repo){
        this.repo = repo;
    }


    public List<RestaurantDto> fetchAllRestaurants() {
        List<Restaurant> restaurantList = repo.findAll();
        return restaurantList.stream()
                .map(RestaurantMapper.INSTANCE::mapRestaurantToRestaurantDto)
                .toList();
    }


	public RestaurantDto addRestaurantInDB(RestaurantDto restaurantDTO) {
		Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDTO);
		Restaurant result = repo.save(restaurant);
		RestaurantDto resDto = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(result);
		return resDto;
	}


	 public ResponseEntity<RestaurantDto> fetchRestaurantById(Integer id) {
	        Optional<Restaurant> restaurant =  repo.findById(id);
	        if(restaurant.isPresent()){
	            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(restaurant.get()), HttpStatus.OK);
	        }
	        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    }


	public RestaurantDto updateRestaurantInDb(RestaurantDto restaurantDto) {
		Restaurant rest = RestaurantMapper.INSTANCE.mapRestaurantDtoToRestaurant(restaurantDto);
		Optional<Restaurant> existingRestaurant  =  repo.findById(rest.getId());
		if (existingRestaurant.isPresent()) {
            Restaurant restaurant = existingRestaurant.get();
            restaurant.setName(rest.getName());
            restaurant.setAddress(rest.getAddress());
            restaurant.setCity(rest.getCity());
            restaurant.setRestaurantDescription(rest.getRestaurantDescription());
            Restaurant updatedRestaurant = repo.save(restaurant);
            return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(updatedRestaurant);
        } else {
            throw new RuntimeException("Restaurant not found with ID: " + rest.getId());
        }
	}


	public RestaurantDto deleteById(Integer id) {
		Optional<Restaurant> existingRestaurant  =  repo.findById(id);
		if (existingRestaurant.isPresent()) {
			repo.deleteById(id);
			 return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDto(existingRestaurant.get());
		}else {
			throw new RuntimeException("Restaurant not found with ID: " + id);
		}
	}
}
