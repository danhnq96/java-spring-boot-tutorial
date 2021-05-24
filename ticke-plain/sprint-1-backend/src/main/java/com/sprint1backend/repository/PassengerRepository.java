package com.sprint1backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint1backend.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    @Query(value = "select * from passenger where email like %?1% or full_name like %?1% or phone_number like %?1%", nativeQuery = true)
    Page<Passenger> findByEmailAndFullNameAndPhoneNumber(String all, Pageable pageable);

    @Query(value = "select * from passenger where email like  %?1%", nativeQuery = true)
    Page<Passenger> findByEmail(String email, Pageable pageable);

    @Query(value = "select * from passenger where full_name like  %?1%", nativeQuery = true)
    Page<Passenger> findByFullName(String fullName, Pageable pageable);

    @Query(value = "select * from passenger where phone_number like  %?1%", nativeQuery = true)
    Page<Passenger> findByPhoneNumber(String phoneNumber, Pageable pageable);

    Passenger findPassengerById(Long passengerId);
}
