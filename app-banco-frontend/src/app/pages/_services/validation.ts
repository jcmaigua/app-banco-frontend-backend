import { AbstractControl } from '@angular/forms';
import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

/**
 * Componente de validaciones
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
@Injectable({
  providedIn: 'root'
})
export class ValidationService {
  private isEmpty(value: unknown): boolean {
    return value === '' || value === null || value === undefined;
  }

  private validateRequired(value: unknown, message: string): boolean {
    if (this.isEmpty(value)) {
      Swal.fire('Campo requerido', message, 'error');
      return false;
    }
    return true;
  }

  /**
   * Metodo para validar mensaje de error
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  getErrorMessage(error: any): string {
    if (error?.error?.message) return error.error.message;
    if (error?.error?.mensaje) return error.error.mensaje;
    if (error?.message) return error.message;
    return 'Error desconocido';
  }

  /**
   * Metodo para validar contraseña
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarContrasena(parram: any): boolean {
    return this.validateRequired(parram, 'La contraseña es requerida');
  }

  /**
   * Metodo para validar contraseña
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarContrasenaNueva(parram: any): boolean {
    return this.validateRequired(parram, 'La contraseña es nueva es requerida');
  }

  /**
   * Metodo para validar contraseña
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarContrasenaAntigua(parram: any): boolean {
    return this.validateRequired(parram, 'La contraseña es antigua es requerida');
  }

  /**
   * Metodo para validar confirmar contraseña
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarConfirmarContrasena(parram: any): boolean {
    return this.validateRequired(parram, 'La confirmación de contraseña es requerida');
  }

  /**
   * Metodo para comparar contraseñas
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarCompararContrasena(parram1: any, parram2: any): boolean {
    if (parram1 !== parram2) {
      Swal.fire('Campo requerido', 'Las contraseñas de confirmación no coincide', 'error');
      return false;
    }
    return true;
  }

  /**
   * Metodo para comparar contraseñas
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarCompararContrasenaAntigua(parram1: any, parram2: any): boolean {
    if (parram1 !== parram2) {
      Swal.fire('Campo requerido', 'Las contraseñas antigua no coincide', 'error');
      return false;
    }
    return true;
  }

  /**
   * Metodo para validar edad
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  validarEdad(parram: any): boolean {
    if (parram == null || parram === 0) {
      Swal.fire('Campo requerido', 'La edad es requerido', 'error');
      return false;
    }
    return true;
  }

}