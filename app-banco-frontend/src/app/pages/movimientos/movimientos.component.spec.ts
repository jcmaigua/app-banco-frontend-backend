import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { MovimientosComponent } from './movimientos.component';
import { MovimientoService } from 'src/app/services/movimiento.service';
import { CuentaService } from 'src/app/services/cuenta.service';
import { TipoMovimientoService } from 'src/app/services/tipo-movimiento.service';
import { ValidationService } from '../_services/validation';

describe('MovimientosComponent', () => {
  let fixture: ComponentFixture<MovimientosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      declarations: [MovimientosComponent],
      providers: [MovimientoService, CuentaService, TipoMovimientoService, ValidationService],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(MovimientosComponent);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(fixture.componentInstance).toBeTruthy();
  });
});
