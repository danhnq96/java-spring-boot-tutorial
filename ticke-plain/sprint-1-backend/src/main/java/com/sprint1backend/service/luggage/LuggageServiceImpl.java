package com.sprint1backend.service.luggage;

import com.sprint1backend.entity.Luggage;
import com.sprint1backend.repository.LuggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageServiceImpl implements LuggageService {
    @Autowired
    LuggageRepository luggageRepository;

    @Override
    public Luggage findByLuggageId(Long id) {
        return this.luggageRepository.findById(id).orElse(null);
    }

    @Override
    public List<Luggage> findAllLuggage() {
        return luggageRepository.findAll();
    }

    @Override
    public List<Luggage> findAll() {
        return this.luggageRepository.findAll();
    }
}
