package com.technologyplan.service;

import com.technologyplan.dto.HashtagDTO;
import com.technologyplan.dto.Technology.TechnologyInfoDTO;

import java.util.List;

public interface HashtagService {

    List<String> getAllNameHashtagByTechnologyId(Long id);

}
