package com.endgame.adminservice.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ImageDTO {
    private Long id;
    private String small = "https://firebasestorage.googleapis.com/v0/b/endgame-eproject.appspot.com/o/Employees%2Fimage_none.png?alt=media&token=c07b3f80-983e-4ec9-aca2-2eb7030e76b9";
    private String medium = "https://firebasestorage.googleapis.com/v0/b/endgame-eproject.appspot.com/o/Employees%2Fimage_none.png?alt=media&token=c07b3f80-983e-4ec9-aca2-2eb7030e76b9";
    private String big = "https://firebasestorage.googleapis.com/v0/b/endgame-eproject.appspot.com/o/Employees%2Fimage_none.png?alt=media&token=c07b3f80-983e-4ec9-aca2-2eb7030e76b9";
    private boolean mainImage;
    private Long productId;
}
