package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


}
