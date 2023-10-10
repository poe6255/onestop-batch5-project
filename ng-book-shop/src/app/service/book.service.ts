import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, find, map } from 'rxjs';
import { Book } from '../data/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookSubject:BehaviorSubject<Book[]>= new BehaviorSubject<Book[]>([]);

  $books:Observable<Book[]> = this.bookSubject.asObservable();

  constructor( private http:HttpClient) {
    this.init();
   }

  init(){
      this.getAllBook().subscribe({
        next: data=>  this.bookSubject.next(data),
        error : err=> console.log('error'),
        complete: ()=> console.log('success')
      })
  }

  findBookById(id:number):Observable<Book>{
      return this.$books
      .pipe(
        map(e => e.find(b  => b.id == id))
      ) as Observable<Book>;
  }
  private getAllBook():Observable<Book[]>{
    return this.http
    .get<Book[]>('http://localhost:8080/book-list')
  }
}
