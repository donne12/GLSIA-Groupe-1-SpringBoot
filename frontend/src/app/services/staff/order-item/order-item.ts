import { Product } from "../../product/product.service";
import { Order } from "../order/order";

export class OrderItem {
    public id?:number;
    public quantity?:number;
    public price?:number;
    public order?:Order;
    public product?:Product
}
