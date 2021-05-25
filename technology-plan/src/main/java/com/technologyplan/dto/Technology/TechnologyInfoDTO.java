package com.technologyplan.dto.Technology;

import com.technologyplan.dto.HashtagDTO;
import com.technologyplan.dto.TechnologyPlanContentDTO;
import com.technologyplan.entity.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyInfoDTO {

    private Long id;
    private String technologyName;
    private List<HashtagDTO> hashtagList;
}
