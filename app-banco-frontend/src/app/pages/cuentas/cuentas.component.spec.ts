import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { CuentasComponent } from './cuentas.component';
import { CuentaService } from 'src/app/services/cuenta.service';
import { TipoCuentaService } from 'src/app/services/tipo-cuenta.service';
import { UserService } from 'src/app/services/user.service';
import { ValidationService } from '../_services/validation';

describe('CuentasComponent', () => {
  let fixture: ComponentFixture<CuentasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      declarations: [CuentasComponent],
      providers: [CuentaService, TipoCuentaService, UserService, ValidationService],
      schemas: [NO_ERRORS_SCHEMA],
    }).compileComponents();

    fixture = TestBed.createComponent(CuentasComponent);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(fixture.componentInstance).toBeTruthy();
  });
});
