export class ProductExportExcelDTO {
    index: number;
    name: string;
    categoryName: string;
    newPrice: number;
    oldPrice: number;
    discount: number;
    cartCount: number;
    weight: number;
    size: number;
    availibilityCount: number;
    createdDate: string;
    updatedDate: string;
    active: boolean;

    constructor(index: number, name: string, categoryName: string, newPrice: number,
        oldPrice: number, discount: number, cartCount: number, weight: number, size: number,
        availibilityCount: number, createdDate: string, updatedDate: string, active: boolean) {
        this.index = index;
        this.name = name;
        this.categoryName = categoryName;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.discount = discount;
        this.cartCount = cartCount;
        this.weight = weight;
        this.size = size;
        this.availibilityCount = availibilityCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.active = active;
    }
}