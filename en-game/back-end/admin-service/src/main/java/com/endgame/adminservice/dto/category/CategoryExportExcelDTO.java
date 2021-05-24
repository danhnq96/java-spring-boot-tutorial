package com.endgame.adminservice.dto.category;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class CategoryExportExcelDTO {
    private int id;
    private int parentId;
    private String name;
    private boolean active;
    private boolean hasSubCategory;
}
