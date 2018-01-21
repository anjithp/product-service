export class Product {

    public name: string;
    public price: number;
    public by: string;
    public color: string;
    public size: string;
  
    constructor(name: string, price: number, by: string, color: string, size: string) {
      this.name = name;
      this.price = price;
      this.by = by;
      this.color = color;
      this.size = size;
    }
}