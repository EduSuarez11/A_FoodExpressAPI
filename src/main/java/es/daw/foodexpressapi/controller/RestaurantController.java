package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.RestaurantDTO;
import es.daw.foodexpressapi.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());

    }

    // PENDIENTE!! no usar optional. Gestionar excepción sQLException y devolver 404....
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RestaurantDTO> create(@RequestBody RestaurantDTO restaurantDTO) {

        Optional<RestaurantDTO> result = restaurantService.create(restaurantDTO);

        //return ResponseEntity.ok(result.get()); //200
        if (result.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.get()); //201
        }

        return ResponseEntity.notFound().build(); // 404

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (restaurantService.delete(id)) {
            return ResponseEntity.noContent().build(); //ok sin contenido
        }
        return ResponseEntity.notFound().build(); //no ha encontrado el restaurante a borrar
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> update(@PathVariable Long id,
                                                @Valid @RequestBody RestaurantDTO restaurantDTO) {

        return ResponseEntity.ok(restaurantService.update(id, restaurantDTO));

    }

}
