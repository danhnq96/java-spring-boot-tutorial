package com.sprint1backend.service.promotion;
import com.sprint1backend.entity.Promotion;
import com.sprint1backend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public void save(Promotion promotion) {
        this.promotionRepository.save(promotion);
    }

    @Override
    public Promotion findById(long id) {
        return this.promotionRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    @Override
    public void remove(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public List<Promotion> search(String inputSearch, String key) {
        return null;
    }

    @Override
    public List<Promotion> findAll() {
        return this.promotionRepository.findAll();
    }

    @Override
    public List<Promotion> getPromoByDepartureDate(LocalDate promotion) {
        return this.promotionRepository.findByDepartureDate(promotion);
    }

    @Override
    public List<Promotion> getPromoByFlight(String flight) {
        return this.promotionRepository.findByFlightContaining(flight);
    }
//

}
