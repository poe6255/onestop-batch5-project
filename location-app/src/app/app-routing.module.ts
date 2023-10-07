import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DivisionComponent } from './division/division.component';
import { TownshipComponent } from './township/township.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, title: 'Location | Home' },
  { path: 'division', component: DivisionComponent, title: 'Location | Division'},
  { path: 'township', component: TownshipComponent, title: 'Location | Township' },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
