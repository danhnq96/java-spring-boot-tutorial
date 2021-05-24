package com.endgame.adminservice.dto.image;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UploadImageMainDTO {
    private ImageDTO imageMainNew;
    private ImageDTO imageMainOld;
}
