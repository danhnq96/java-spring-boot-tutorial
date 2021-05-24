package com.sprint1backend.controller;
import com.sprint1backend.entity.Promotion;
import com.sprint1backend.service.promotion.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/promotion")
@CrossOrigin
public class PromotionController {

    @Autowired
    PromotionService promotionService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Promotion>> getAll() {
        List<Promotion> promotionList = promotionService.findAll();
        if (promotionList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(promotionList, HttpStatus.OK);
        }
    }
    @GetMapping("/getPromoById/{id}")
    public Promotion getPromotion(@PathVariable("id") Long id) {
        return promotionService.findById(id);
    }

   @PostMapping("/addNewPromo")
    public ResponseEntity<Void> addPromotion(@RequestBody Promotion promotion) {
        if (promotion == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            promotionService.save(promotion);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @DeleteMapping("/deletePromo/{id}")
    public ResponseEntity<Void> deletePromo (@PathVariable Long id) {
       promotionService.remove(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updatePromo/{id}")
    public ResponseEntity<Void> updatePromotion(@RequestBody Promotion promotion, @PathVariable long id) {
        Promotion promotion1 = promotionService.findById(id);
        if (promotion1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            promotion1.setFlight(promotion.getFlight());
            promotion1.setAirline(promotion.getAirline());
            promotion1.setFlightNumber(promotion.getFlightNumber());
            promotion1.setDepartureDate(promotion.getDepartureDate());
            promotion1.setDeparturePlace(promotion.getDeparturePlace());
            promotion1.setDepartureTime(promotion.getDepartureTime());
            promotion1.setArrivalPlace(promotion.getArrivalPlace());
            promotion1.setArrivalTime(promotion.getArrivalTime());
            promotion1.setInformation(promotion.getInformation());
            promotion1.setPrice(promotion.getPrice());
            promotionService.update(promotion1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @GetMapping("/getPromoByFlight/{flight}")
    public List<Promotion> getPromotionByFlight(@PathVariable("flight") String flight) {
        return promotionService.getPromoByFlight(flight);
    }
    @GetMapping("/getPromoByDepartureDate")
    public List<Promotion> getPromotionByDepartureDate(@RequestParam String promotion) {
        System.out.println(promotion);
        Date date = new Date(promotion);
        System.out.println(date.toString());
        LocalDate localDate= LocalDate.of(date.getYear() + 1900, date.getMonth()+1, date.getDate());
        return promotionService.getPromoByDepartureDate(localDate);
//        Date date = new Date(promotion);
//        System.out.println(date.toString());
//        LocalDate localDate = LocalDate.of(date.getYear() + 1900,date.getMonth()+1,date.getDate());
//        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);
//        return promotionService.getPromoByDepartureDate(dayOfWeek.name());
//
    }
}
