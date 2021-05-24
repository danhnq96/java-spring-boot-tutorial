import { ColorDTO } from "../Color/colorDTO";
import { ImageDTO } from "../image/imageDTO";

export class ProductDTO {
    id: number;
    categoryId: number;
    name: string;
    newPrice: number = 0;
    oldPrice: number = 0;
    discount: number = 0;
    ratingCount: number = 0;
    ratingValue: number = 0;
    availibilityCount: number = 0;
    cartCount: number = 0;
    weight: number = 0;
    size: string;
    description: string;
    active: boolean;
    // createdDate: string;
    images: ImageDTO[];
    imagesDeleted: ImageDTO[];
    imagesAdd: ImageDTO[];
    imageMain: ImageDTO;
    colors: ColorDTO[]
}