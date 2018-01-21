import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';

import { AppComponent } from './app/app.component';
import {  FormsModule } from '@angular/forms';
import { SearchComponent } from './search/search.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchService } from './services/search.service';


@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    SearchResultsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [SearchService],
  bootstrap: [AppComponent]
})
export class AppModule {
   
 }
