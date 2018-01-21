import { Http } from "@angular/http";
import { Injectable } from "@angular/core";
import { Product } from "../domain/product.model";
import { Headers, Response } from "@angular/http";

import 'rxjs/Rx';
import { Subject } from "rxjs/Subject";

@Injectable()
export class SearchService {

     product : Product;
    productChanged:Subject<Product> = new Subject<Product>();

    constructor(private http: Http) {

    }

    getProduct(id: string) {
        const headers: Headers = new Headers({'Content-Type': 'application/json'});
        this.http.get('http://localhost:8080/core/products/' + id, {headers: headers})
        .subscribe((response:Response) => {
            let prodFromServer = response.json();
            this.product = new Product(prodFromServer.name,prodFromServer.price,
                prodFromServer.attributes.manufacturer,prodFromServer.attributes.color,
                prodFromServer.attributes.size);
        this.productChanged.next(this.product);
        });
    }

}