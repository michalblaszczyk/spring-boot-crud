package com.example.springbootcrud.controller;

import com.example.springbootcrud.exception.ResourceNotFoundException;
import com.example.springbootcrud.model.City;
import com.example.springbootcrud.repository.CityRepository;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crud")
public class CityContoller {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/city")
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    @GetMapping("city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id : " + id));
        return ResponseEntity.ok().body(city);
    }

    @PostMapping("/city")
    public City createCity(@Valid @RequestBody City city){
        return cityRepository.save(city);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceRequestDeniedException("City not found for this id : " + id));
        cityRepository.delete(city);
        return ResponseEntity.ok().build();
    }
}
