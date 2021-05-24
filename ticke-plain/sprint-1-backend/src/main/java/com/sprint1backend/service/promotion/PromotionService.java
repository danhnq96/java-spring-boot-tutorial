package com.sprint1backend.service.promotion;
import com.sprint1backend.entity.Promotion;

import java.time.LocalDate;
import java.util.List;


public interface PromotionService {
     void save(Promotion promotion);
     Promotion findById(long id);
     void update(Promotion promotion);
     void remove(long id);
     List<Promotion> search(String inputSearch, String key);
     List<Promotion> findAll();
     List<Promotion> getPromoByDepartureDate(LocalDate promotion);
     List<Promotion> getPromoByFlight(String flight);
//
}
