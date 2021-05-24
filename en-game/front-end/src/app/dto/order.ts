import { Product } from "../app.models";

export enum OrderStatus {
  PROCESSING,
  CHARGED,
  COMPLETED,
  PENDING,
  REFUND,
}

export interface ProductDTO {
  productId: number;
  name: string;
  images: Array<any>;
  oldPrice: number;
  newPrice: number;
  discount: number;
  ratingsCount: number;
  ratingsValue: number;
  description: string;
  availibilityCount: number;
  cartCount: number;
  color: Array<string>;
  size: Array<string>;
  weight: number;
  categoryId: number;
}

export interface Order {
  id?: number;
  orderCode: string;
  userId: number;
  shippingId: number;
  employeeId: number;
  orderDetails: ProductDTO[];
  amount: number;
  discount: number;
  tax: number;
  shippingFee: number;
  paymentId?: string;
  status: OrderStatus;
  createdAt?: number;
  updateAt?: number;
}

export interface OrderRequest {
  userId: number;
  pageNumber?: number;
  pageSize?: number;
}

export interface CountOrderStatus {
  count: number;
  status: OrderStatus;
}
