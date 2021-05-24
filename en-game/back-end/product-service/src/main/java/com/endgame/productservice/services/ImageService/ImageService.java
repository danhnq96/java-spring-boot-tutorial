package com.endgame.productservice.services.ImageService;

import com.endgame.productservice.entity.Image;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    void saveImage(Image image);
}
