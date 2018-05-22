import { Component, OnInit } from '@angular/core';


import { TasaRepresentativaService } from './tasa-representativa.service';
import { Tcrm } from '../domain/Tcrm'

@Component({
  selector: 'app-tasa-representativa',
  templateUrl: './tasa-representativa.component.html',
  styleUrls: ['./tasa-representativa.component.css']
})
export class TasaRepresentativaComponent implements OnInit {

  trmActual: Tcrm = new Tcrm;

  constructor(private tasaRepresentativaService: TasaRepresentativaService) { }

  ngOnInit() {
    this.getTicketsVigentes();
  }

  getTicketsVigentes(){
    this.tasaRepresentativaService.getTRM().then(trm => {
      this.trmActual = <Tcrm>trm;
    })
  }



}
