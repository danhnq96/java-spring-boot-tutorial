package com.sprint1backend.service.luggage;

import com.sprint1backend.entity.Luggage;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LuggageService {
    public Luggage findByLuggageId(Long id);
    public List<Luggage> findAllLuggage();
    List<Luggage> findAll();
}
