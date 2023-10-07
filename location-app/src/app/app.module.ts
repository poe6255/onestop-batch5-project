import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { DivisionComponent } from './division/division.component';
import { TownshipComponent } from './township/township.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginFormComponent } from './util/widgets/login-form/login-form.component';
import { FormGroupComponent } from './util/widgets/form-group/form-group.component';
import { ModalDialogComponent } from './util/widgets/modal-dialog/modal-dialog.component';
import { SearchFormComponent } from './util/widgets/search-form/search-form.component';
import { MasterDataComponent } from './util/widgets/master-data/master-data.component';
import { NoDataComponent } from './util/widgets/no-data/no-data.component';
import { DivisionFormComponent } from './division/division-form/division-form.component';
import { TownshipListComponent } from './township/township-list/township-list.component';
import { TownshipSearchComponent } from './township/township-search/township-search.component';
import { TownshipFormComponent } from './township/township-form/township-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DivisionComponent,
    TownshipComponent,
    LoginFormComponent,
    FormGroupComponent,
    ModalDialogComponent,
    SearchFormComponent,
    MasterDataComponent,
    NoDataComponent,
    DivisionFormComponent,
    TownshipListComponent,
    TownshipSearchComponent,
    TownshipFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
