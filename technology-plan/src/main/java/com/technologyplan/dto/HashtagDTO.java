package com.technologyplan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HashtagDTO {

    private Long id;
    private String hashtagName;
    private String urlLink;

}
