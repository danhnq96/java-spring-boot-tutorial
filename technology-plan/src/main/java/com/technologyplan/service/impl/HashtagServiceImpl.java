package com.technologyplan.service.impl;

import com.technologyplan.dto.HashtagDTO;
import com.technologyplan.dto.Technology.TechnologyInfoDTO;
import com.technologyplan.service.HashtagService;
import com.technologyplan.service.TechnologyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class HashtagServiceImpl implements HashtagService {

    @Autowired
    private TechnologyService technologyService;

    @Override
    public List<String> getAllNameHashtagByTechnologyId(Long id) {

        List<TechnologyInfoDTO> technologyInfList = this.technologyService.getAllTechnologyInfoByProjectId(id);

        List<HashtagDTO> hashtagList = new ArrayList<HashtagDTO>();
        for (TechnologyInfoDTO technology : technologyInfList) {
            hashtagList.addAll(technology.getHashtagList());
        }

        // counting value && return map (key - value)
        Map<String, Long> mapHashtag = hashtagList.stream().collect(groupingBy(HashtagDTO::getHashtagName, counting()));

        // get top 5 value high - sort
        List<String> listHashtag = mapHashtag.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5).map(Map.Entry::getKey).collect(Collectors.toList());

        return listHashtag;
    }

}
