package com.sprint1backend.controller;

import com.sprint1backend.entity.Luggage;
import com.sprint1backend.service.luggage.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/luggage")
@CrossOrigin
public class LuggageController {
    @Autowired
    LuggageService luggageService;

    @GetMapping()
    public ResponseEntity<List<Luggage>> getAll(){
        return new ResponseEntity<>(this.luggageService.findAll(), HttpStatus.OK);
    }
}
