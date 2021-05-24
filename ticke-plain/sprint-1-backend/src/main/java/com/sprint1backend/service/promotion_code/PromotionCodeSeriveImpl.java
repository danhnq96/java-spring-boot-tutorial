package com.sprint1backend.service.promotion_code;

import com.sprint1backend.entity.Promotion;
import com.sprint1backend.repository.PromotionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionCodeSeriveImpl implements PromotionCodeService {

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @Override
    public List<Promotion> fillAllPromotion() {
        return promotionCodeRepository.findAll();
    }
}
