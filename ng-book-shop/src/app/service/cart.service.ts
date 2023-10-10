import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Book } from '../data/book';
@Injectable({
  providedIn: 'root'
})
export class CartService {

    private cartSubject:BehaviorSubject <Book[]>=
    new BehaviorSubject<Book[]> ([]);
    $cart:Observable<Book[]> =this.cartSubject.asObservable();

  constructor() { 
    
  }

}
