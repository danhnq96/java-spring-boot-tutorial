package com.endgame.adminservice.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCategoryDTO {
    private int id;
    private String name;
    private boolean active;
    private boolean hasSubCategory;
}
