import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TipoCuentaService } from './tipo-cuenta.service';

describe('TipoCuentaService', () => {
  let service: TipoCuentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TipoCuentaService],
    });
    service = TestBed.inject(TipoCuentaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
