package com.example.springbootcrud.controller;

import com.example.springbootcrud.exception.ResourceNotFoundException;
import com.example.springbootcrud.model.City;
import com.example.springbootcrud.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
public class CityContoller {
    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/city")
    public List<City> getAllCities(){
        return (List<City>) cityRepository.findAll();
    }

    @GetMapping("city/{id}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id : " + id));
        return ResponseEntity.ok().body(city);
    }


}
