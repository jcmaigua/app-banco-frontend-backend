import Swal from 'sweetalert2';
import { ValidationService } from './validation';

describe('ValidationService', () => {
  let service: ValidationService;
  let swalSpy: jest.SpyInstance;

  beforeEach(() => {
    service = new ValidationService();
    swalSpy = jest.spyOn(Swal, 'fire').mockImplementation(() => Promise.resolve({} as any));
  });

  afterEach(() => {
    swalSpy.mockRestore();
  });

  it('returns error message by backend priority', () => {
    expect(service.getErrorMessage({ error: { message: 'm1' } })).toBe('m1');
    expect(service.getErrorMessage({ error: { mensaje: 'm2' } })).toBe('m2');
    expect(service.getErrorMessage({ message: 'm3' })).toBe('m3');
    expect(service.getErrorMessage({})).toBe('Error desconocido');
  });

  it('validarContrasena returns false when empty', () => {
    expect(service.validarContrasena('')).toBe(false);
    expect(swalSpy).toHaveBeenCalled();
  });

  it('validarContrasena returns true when valid', () => {
    expect(service.validarContrasena('abc123')).toBe(true);
    expect(swalSpy).not.toHaveBeenCalled();
  });

  it('validarCompararContrasena validates equality', () => {
    expect(service.validarCompararContrasena('a', 'b')).toBe(false);
    expect(service.validarCompararContrasena('a', 'a')).toBe(true);
  });

  it('validarEdad rejects zero age', () => {
    expect(service.validarEdad(0)).toBe(false);
    expect(service.validarEdad(18)).toBe(true);
  });
});
