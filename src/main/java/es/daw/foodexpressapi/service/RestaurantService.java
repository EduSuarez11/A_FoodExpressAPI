package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import es.daw.foodexpressapi.mapper.RestaurantMapper;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                //.map(this::toDTO)
                .map(restaurantMapper::toDTO)
                .toList();
    }


    public Optional<RestaurantDTO> create(RestaurantDTO restaurantDTO) {
        //Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
        Restaurant restaurant = toEntity(restaurantDTO);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        //return Optional.of(restaurantMapper.toDTO(savedRestaurant));
        return Optional.of(toDTO(savedRestaurant));
    }

    public Restaurant toEntity(RestaurantDTO dto){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        return restaurant;
    }
    private RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setPhone(restaurant.getPhone());
        return dto;
    }


}
