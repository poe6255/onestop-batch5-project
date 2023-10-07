import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor() {}

  private active: any

  set user(data: any) {
    this.active = data
  }

  get user() {
    return this.active
  }

  logOut() {
    this.active = undefined
  }
}
