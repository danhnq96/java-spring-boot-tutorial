package com.sprint1backend.service.feedback.impl;

import com.sprint1backend.entity.Feedback;
import com.sprint1backend.repository.feedback.FeedBackRepository;
import com.sprint1backend.service.feedback.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public String sendFeedback(Feedback feedback) {
        this.feedBackRepository.save(feedback);
         return "OK";
    }
}
