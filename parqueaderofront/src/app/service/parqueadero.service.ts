import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { TIPO_VEHICULOS } from './tipo-vehiculos';
import { Vehiculo } from '../domain/vehiculo';
import { Ticket } from '../domain/Ticket';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ParqueaderoService {

  private url = "http://localhost:8080/celador/";

  constructor(private http: HttpClient) { }

  getTipoVehiculos(){
    return of(TIPO_VEHICULOS);
  }

  realizarIngreso(vehiculo: Vehiculo){
    return this.http.post(`${this.url}ingreso`,vehiculo,httpOptions).toPromise();
  }

  realizarSolicitudRetiro(id: number){
    return this.http.get(`${this.url}retiro/solicitud?id=${id}`,httpOptions).toPromise();
  }

  confirmarRetiro(ticket: Ticket){
    return this.http.post(`${this.url}retiro/solicitud`,ticket,httpOptions).toPromise();
  }

  getTicketsVigentes(){
    return this.http.get(`${this.url}vehiculos/actuales`,httpOptions).toPromise();
  }
}
