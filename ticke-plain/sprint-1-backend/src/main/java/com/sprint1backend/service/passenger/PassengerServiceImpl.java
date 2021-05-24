package com.sprint1backend.service.passenger;

import com.sprint1backend.entity.Passenger;
import com.sprint1backend.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService{
    @Autowired
    PassengerRepository passengerRepository;
    @Override
    public void savePassengerListPassenger(Passenger passenger) {
        this.passengerRepository.save(passenger);
        if (passenger.getPassengerList().size() != 0) {
            for (Passenger i: passenger.getPassengerList()){
                i.setPassenger(passenger);
                this.passengerRepository.save(i);
            }
        }
    }
}
