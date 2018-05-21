import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TasaRepresentativaComponent } from './tasa-representativa.component';

describe('TasaRepresentativaComponent', () => {
  let component: TasaRepresentativaComponent;
  let fixture: ComponentFixture<TasaRepresentativaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TasaRepresentativaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TasaRepresentativaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
