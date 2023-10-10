import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from'@angular/common/http';
import { BooListComponent } from './book/boo-list/boo-list.component';
import { NavbarComponent } from './navbar/navbar.component';
import { BookCardComponent } from './book/book-card/book-card.component';
import { FooterComponent } from './footer/footer.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { AuthorDetailsComponent } from './book/author-details/author-details.component';
import { BookReviewComponent } from './book/book-review/book-review.component';

@NgModule({
  declarations: [
    AppComponent,
    BooListComponent,
    NavbarComponent,
    BookCardComponent,
    FooterComponent,
    BookDetailsComponent,
    AuthorDetailsComponent,
    BookReviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
