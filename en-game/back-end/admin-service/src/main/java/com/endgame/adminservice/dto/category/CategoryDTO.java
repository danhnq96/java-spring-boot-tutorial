package com.endgame.adminservice.dto.category;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long id;
    @Pattern(regexp = "[\\p{L}\\s]{1,30}", message = "First name is less than 30 characters and no special characters;")
    @NotBlank(message = "First name is required;")
    private String name;
    private int parentId;
    private boolean active;
    private boolean hasSubCategory;
}
