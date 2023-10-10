import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Observable, map, switchMap } from 'rxjs';
import { Book } from 'src/app/data/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent  implements OnInit {

  //$book!:Observable<Book>;

  constructor(private route:ActivatedRoute,private  bookservice:BookService){

  }
    book!:Book
  ngOnInit(): void {
   // const id = this.route.snapshot.paramMap.get('id')!;
  //  console.log("================", id);
   // this.$book=  this.bookservice.findBookById(parseInt(id));

  //this.route.paramMap.subscribe((param:ParamMap) => {
    //const id =param.get('id')!;
   // console.log("======",id)
   // this.$book = this.bookservice.findBookById(parseInt(id))
  // this.bookservice.findBookById(parseInt(id)).subscribe(data => this.book=data)


  // this.route.paramMap.pipe(
  //     map( (param:ParamMap)=> param.get('id')!),
  //     switchMap(id => this.bookservice.findBookById(parseInt(id)))
  // )
  // .subscribe(data => this.book =data);
  // }

    this.book = this.route.snapshot.data['book'];
  }

}
