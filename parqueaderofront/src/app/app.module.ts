import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { ParqueaderoComponent } from './parqueadero/parqueadero.component';
import { TasaRepresentativaComponent } from './tasa-representativa/tasa-representativa.component';

@NgModule({
  declarations: [
    AppComponent,
    ParqueaderoComponent,
    TasaRepresentativaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
