package com.sprint1backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {
    private Long id;
    private String fullName;
    private LocalDate birthday;
    private String address;
    private String email;
    private Boolean gender;
    private String phoneNumber;
    private String identityNumber;
    private Long luggageId;
    private String nameLuggage;
}
