import { Component, OnInit } from '@angular/core';
import { SearchService } from '../services/search.service';
import { Product } from '../domain/product.model';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
   product: Product = null;

  constructor(private searchService: SearchService) { }

  ngOnInit() {
  this.searchService.productChanged.subscribe((product: Product) => {
    this.product = product;
  });
  }

}
