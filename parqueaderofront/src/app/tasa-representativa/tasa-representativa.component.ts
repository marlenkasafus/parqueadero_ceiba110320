import { Component, OnInit } from '@angular/core';

import { TasaRepresentativaService } from './tasa-representativa.service';

@Component({
  selector: 'app-tasa-representativa',
  templateUrl: './tasa-representativa.component.html',
  styleUrls: ['./tasa-representativa.component.css']
})
export class TasaRepresentativaComponent implements OnInit {

  constructor(private tasaRepresentativaService: TasaRepresentativaService) { }

  ngOnInit() {
  }



}
