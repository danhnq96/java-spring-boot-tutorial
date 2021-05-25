package com.technologyplan.dto;

import com.technologyplan.dto.Technology.TechnologyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyPlanContentDTO {

    private Long id;
    private String content;
    private TechnologyDTO technology;

}
