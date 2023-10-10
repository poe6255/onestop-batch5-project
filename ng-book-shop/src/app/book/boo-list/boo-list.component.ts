import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from 'src/app/data/book';
import { BookService } from 'src/app/service/book.service';
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-boo-list',
  templateUrl: './boo-list.component.html',
  styleUrls: ['./boo-list.component.scss']
})
export class BooListComponent implements OnInit {
//$books!:Observable<Book[]>;
//$cart!:Observable<Book[]>;

  constructor (public bookservice:BookService,
    public cartservice:CartService){

  }

  ngOnInit(): void {
    // this.$books= this.bookservice.$books;
     //this.$cart=this.cartservice.$cart;
  }

}
