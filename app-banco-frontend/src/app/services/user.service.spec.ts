import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { environment } from '../../environments/environment';

describe('UserService', () => {
  let service: UserService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService],
    });
    service = TestBed.inject(UserService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    http.verify();
  });

  it('saveInfoPersona envía POST al recurso clientes/saveInfoPersona', (done) => {
    const body = { userRequest: 'u', payload: { nombre: 'N' } };
    service.saveInfoPersona(body).subscribe((res) => {
      expect(res.status).toBe('OK');
      done();
    });
    const req = http.expectOne(
      (r) =>
        r.url.startsWith(environment.msAdminConfig) &&
        r.url.includes('clientes/saveInfoPersona') &&
        r.method === 'POST',
    );
    expect(req.request.body).toEqual(body);
    req.flush({ status: 'OK', code: 200, message: '', payload: {} });
  });

  it('retrieveInfoCliente envía GET con id en la ruta', (done) => {
    service.retrieveInfoCliente(7).subscribe((res) => {
      expect(res).toBeDefined();
      done();
    });
    const req = http.expectOne(
      (r) =>
        r.url.includes('clientes/retrieveInfoCliente/7') && r.method === 'GET',
    );
    req.flush({ status: 'OK', code: 200, message: '', payload: { idCliente: 7 } });
  });
});
