package com.sprint2.backend.controller;


import com.sprint2.backend.entity.Image;
import com.sprint2.backend.model.MessageFromCamera;
import com.sprint2.backend.services.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class CameraController {
    @Autowired
    private CameraService cameraService;

    @PostMapping("/api/7/post-src-image")
    public ResponseEntity<MessageFromCamera> sendMessageFromCamera(@RequestBody Image newImage) throws Exception {
        String plateNumber = this.cameraService.getNumberPlateFromImage(newImage);
        MessageFromCamera res = this.cameraService.checkMemberOfCar(plateNumber);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
