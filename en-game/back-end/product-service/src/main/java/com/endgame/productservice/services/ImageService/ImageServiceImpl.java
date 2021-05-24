package com.endgame.productservice.services.ImageService;

import com.endgame.productservice.entity.Image;
import com.endgame.productservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepo;

    @Override
    public void saveImage(Image image) {
        imageRepo.save(image);
    }

}
