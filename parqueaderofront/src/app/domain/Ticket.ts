import { Vehiculo } from '../domain/vehiculo';

export class Ticket {
  id: number;
  vehiculo: Vehiculo;
  fechaIngreso: string;
  estadoParqueaderoEnum: string;
  fechaSalida: string;
  valorPagar: number;

  constructor () {

  }
}