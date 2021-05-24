export class CategoryExportExcelDTO {
    index: number;
    id: number;
    parentId: number;
    name: string;
    active: boolean;
    hasSubCategory: boolean;

    constructor(index: number, id: number, parentId: number, name: string, active: boolean, hasSubCategory: boolean){
        this.index = index;
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.active = active;
        this.hasSubCategory = hasSubCategory;
    }
}