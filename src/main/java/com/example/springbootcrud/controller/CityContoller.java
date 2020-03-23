package com.example.springbootcrud.controller;

import com.example.springbootcrud.exception.ResourceNotFoundException;
import com.example.springbootcrud.model.City;
import com.example.springbootcrud.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
@Slf4j
@RequiredArgsConstructor
public class CityContoller {
    private final CityService cityService;

    @GetMapping("/city")
    public ResponseEntity<List<City>> findAll(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping("/city")
    public ResponseEntity create(@Valid @RequestBody City city){
        return ResponseEntity.ok(cityService.save(city));
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<Optional<City>> findById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(cityService.finndById(id));
    }
    @PutMapping("/city/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City city) throws ResourceNotFoundException {
        return ResponseEntity.ok(cityService.update(id,city));
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws ResourceNotFoundException {
        cityService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
