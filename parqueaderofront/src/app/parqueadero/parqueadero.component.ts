import { Component, OnInit, ViewChild } from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {NgForm} from '@angular/forms';

import { Vehiculo } from '../domain/vehiculo';
import { Ticket } from '../domain/Ticket';

import { ParqueaderoService} from '../service/parqueadero.service';


const SUCCESS: string = "alert-success";
const ERROR: string = "alert-danger";
const WARNING: string = "alert-warning";

@Component({
  selector: 'app-parqueadero',
  templateUrl: './parqueadero.component.html',
  styleUrls: ['./parqueadero.component.css']
})
export class ParqueaderoComponent implements OnInit {

  @ViewChild("modalRetirar")
  modal: NgbModal;

  tipoVehiculos: String[];
  ticketSeleccionado: Ticket;

  vehiculo: Vehiculo = new Vehiculo;
  parqueadero: Ticket;
  mensaje: string;
  claseMensaje: string;
  esError: boolean = false;
  esTicket: boolean = false;
  tickets: Ticket[];
  ingreso: NgForm;

  constructor(private parqueaderoService: ParqueaderoService,private modalService: NgbModal) { 
    
  }

  ngOnInit() {
    this.getTiposVehiculos();
    this.getTicketsVigentes()
  }

  solicitudRetiro(content,id) {
    this.parqueaderoService.realizarSolicitudRetiro(id).then(ticket => {
      this.ticketSeleccionado = <Ticket>ticket;
      this.modalService.open(content, { centered: true });
    });
  }

  ingresar(ingresoForm: NgForm){
    this.parqueaderoService.realizarIngreso(this.vehiculo).then(data => {
      console.log(data);
      this.claseMensaje = SUCCESS;
      this.esTicket = true;
      this.parqueadero = <Ticket> data;
      this.tickets.push(<Ticket>data);
      this.vehiculo = new Vehiculo;
      console.log(ingresoForm);
      ingresoForm.resetForm();
      ingresoForm.reset();
    }, dataError => {
      console.log(dataError);
      this.esError = true;
      this.mensaje = dataError.error;
      if (dataError.status == 406) {
        this.claseMensaje = WARNING;
      }else{
        this.claseMensaje = ERROR;
      }
    })
  }

  confirmarRetiro(ticket: Ticket){
    console.log(ticket);
    this.tickets = this.tickets.filter(h => h.id !== ticket.id);
    this.parqueaderoService.confirmarRetiro(ticket).then(ticketReturn => {
      this.ticketSeleccionado = null;
    });
  }

  getTicketsVigentes(){
    this.parqueaderoService.getTicketsVigentes().then(tickets => {
      this.tickets = <Ticket[]>tickets;
    })
  }
  private getTiposVehiculos(): void {
    this.parqueaderoService.getTipoVehiculos().subscribe(tipoVehiculos => this.tipoVehiculos = tipoVehiculos);
  }

}
