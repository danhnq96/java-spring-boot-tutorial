import { CategoryListProductDTO } from "../Category/CategoryListProductDTO";
import { ImageDTO } from "../image/imageDTO";

export class ListProductDTO {
    id: number;
    name: string;
    category: CategoryListProductDTO;
    newPrice: number;
    oldPrice: number;
    discount: number;
    cartCount: number;
    weight: number;
    availibilityCount: number;
    createdDate: string;
    updatedDate: string;
    image: ImageDTO;
    active: boolean;

}