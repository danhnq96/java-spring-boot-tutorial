package com.sprint3.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCheckThesisDTO {
    Boolean status;
    String checkDate;
    String idCheckThesis;

    String studentGroupId;
    String thesisId;
}
