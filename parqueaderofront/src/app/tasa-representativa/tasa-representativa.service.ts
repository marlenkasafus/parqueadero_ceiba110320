import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TasaRepresentativaService {

  private url = "http://localhost:8080/TCRMService";

  constructor(private http: HttpClient) { }

  getTRM(){
    return this.http.get(`${this.url}`,httpOptions).toPromise();
  }
}
