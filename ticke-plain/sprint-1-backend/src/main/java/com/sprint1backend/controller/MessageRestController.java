package com.sprint1backend.controller;

import com.sprint1backend.entity.IconMessage;
import com.sprint1backend.service.message.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageRestController {

    @Autowired
    private IconService iconService;

    @GetMapping("/icon")
    public ResponseEntity<List<IconMessage>> getIcon() {
        return new ResponseEntity<>(this.iconService.getIcon(), HttpStatus.OK);
    }
}
