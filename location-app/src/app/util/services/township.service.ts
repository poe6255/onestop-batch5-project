import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'projects/location-app/src/environments/environment.development';

const TOWNSHIP_API = `${environment.url}/township`

@Injectable({
  providedIn: 'root'
})
export class TownshipService {

  constructor(private http: HttpClient) { }

  save(form: any) {

    const {id, ...data} = form

    if(id)
      return this.update(id, data)

    return this.create(data)
  }

  private create(data: any) {
    return this.http.post<any>(TOWNSHIP_API, data)
  }

  private update(id: number, data: any) {
    return this.http.put<any>(`${TOWNSHIP_API}/${id}`, data)
  }

  search(form: any) {
    return this.http.get<any[]>(TOWNSHIP_API, {params: form})
  }
}
