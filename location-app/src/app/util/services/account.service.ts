import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'projects/location-app/src/environments/environment.development';

const ACCOUNT_API = `${environment.url}/account`

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  signIn(form: any) {
    return this.http.get<any>(`${ACCOUNT_API}/sign-in`, {params: form})
  }

  signUp(form: any) {
    return this.http.post<any>(`${ACCOUNT_API}/sign-up`, form)
  }
}
