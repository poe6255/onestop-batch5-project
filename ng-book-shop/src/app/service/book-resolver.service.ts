import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { Book } from '../data/book';
import { Observable } from 'rxjs';
import { BookService } from './book.service';

export const BookResolver:ResolveFn<Book> =(route : ActivatedRouteSnapshot) 
:Observable<Book> =>{
  const id = route.paramMap.get('id')!;
  const bookService = inject(BookService);
  return bookService.findBookById(parseInt(id))
}
