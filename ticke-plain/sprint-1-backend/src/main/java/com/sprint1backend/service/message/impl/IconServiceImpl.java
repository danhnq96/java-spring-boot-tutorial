package com.sprint1backend.service.message.impl;

import com.sprint1backend.entity.IconMessage;
import com.sprint1backend.repository.message.IconRepository;
import com.sprint1backend.service.message.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconServiceImpl implements IconService {
    @Autowired
    private IconRepository iconRepository;
    @Override
    public List<IconMessage> getIcon() {
        return this.iconRepository.findAll();
    }
}
