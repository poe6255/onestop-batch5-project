import { Component, Input } from '@angular/core';
import { Book } from 'src/app/data/book';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent {
  @Input()
    book!:Book;
}
