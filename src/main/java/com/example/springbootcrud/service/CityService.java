package com.example.springbootcrud.service;

import com.example.springbootcrud.exception.ResourceNotFoundException;
import com.example.springbootcrud.model.City;
import com.example.springbootcrud.repository.CityRepository;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

    public Optional<City> finndById(Long id) throws ResourceNotFoundException {
        City c = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id: " + id));
        return cityRepository.findById(id);
    }

    public City save(City city){
        return cityRepository.save(city);
    }
    public City update(Long id,City city) throws ResourceNotFoundException {
        City c = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id: " + id));
        c.setCityName(city.getCityName());
        c.setPopulation(city.getPopulation());
        final City cityUpdated = cityRepository.save(c);
        return cityUpdated;
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        City c = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id: " + id));
        cityRepository.deleteById(id);
    }

    public void deleteAll(){
        cityRepository.deleteAll();
    }
}
