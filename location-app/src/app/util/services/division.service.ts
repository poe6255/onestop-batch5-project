import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'projects/location-app/src/environments/environment.development';

const DIVISION_API = `${environment.url}/division`

@Injectable({
  providedIn: 'root'
})
export class DivisionService {

  constructor(private http: HttpClient) { }

  create(form: any) {
    const {id, ...value} = form
    if(id)
      return this.update(id, value)
    return this.save(value)
  }

  private save(form: any) {
    return this.http.post<any>(DIVISION_API, form)
  }

  private update(id: number, form: any) {
    return this.http.put<any>(`${DIVISION_API}/${id}`, form)
  }

  search(form: any) {
    return this.http.get<any[]>(DIVISION_API, {params: form})
  }

  findAllRegions() {
    return this.http.get<any[]>(`${DIVISION_API}/region`)
  }

  delete(id: number) {
    return this.http.delete<any>(`${DIVISION_API}/${id}`)
  }

  upload(file: any) {
    var fd = new FormData
    fd.append('file', file, file.name)

    return this.http.post<any>(`${DIVISION_API}/upload`, fd)
  }
}
