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
public class AppUserDTO {
    String fullName;
    LocalDate birthday;
    String address;
    String email;
    Boolean gender;
    String phoneNumber;
}
