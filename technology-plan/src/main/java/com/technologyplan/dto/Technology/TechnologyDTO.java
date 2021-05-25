package com.technologyplan.dto.Technology;

import com.technologyplan.dto.TechnologyPlanContentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDTO {

    private Long id;
    private String technologyName;
    private List<TechnologyPlanContentDTO> technologyPlanContentList;

}
