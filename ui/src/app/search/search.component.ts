import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchService } from '../services/search.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
   productId: string;

  constructor(private searchService: SearchService) { }

  ngOnInit() {
    
  }

  getProduct() {
    if(this.productId !== null) {
      this.searchService.getProduct(this.productId);
    }
  }
}
