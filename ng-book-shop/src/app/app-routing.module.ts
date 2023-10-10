import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BooListComponent } from './book/boo-list/boo-list.component';
import { BookDetailsComponent } from './book/book-details/book-details.component';
import { AuthorDetailsComponent } from './book/author-details/author-details.component';
import { BookReviewComponent } from './book/book-review/book-review.component';
import { BookResolver } from './service/book-resolver.service';

const routes: Routes = [
  {
    path:'',redirectTo: 'home',pathMatch:'full'
    
  },
  {
    path:'home',component:BooListComponent
  },
  {
    path:'home/book/:id',component:BookDetailsComponent,
      resolve:{
        book : BookResolver
      },
    children:[
      {
        path : 'author',component:AuthorDetailsComponent,
        
      },{
        path : 'review',component:BookReviewComponent
      }
    ]
    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
