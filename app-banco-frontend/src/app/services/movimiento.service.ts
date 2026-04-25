import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { GenericResponseDTO } from '../pages/_dto/generic-response-list-DTO';
@Injectable({
  providedIn: 'root',
})

/**
 * Clase para servicios de movimiento
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
export class MovimientoService {

  /**
   * Variables de los servicios de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private msAdminConfigMgmt = environment.msAdminConfig;
  private typeService = environment.typeService[2];
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  /**
   * Contructor del servicio de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Metodo para guardar movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  saveInfoMovimiento(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/saveInfoMovimiento'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para actualizar movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  updateInfoMovimiento(param: any) {
    return this.httpClient
      .put<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/updateInfoMovimiento'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para retornar movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public retrieveInfoMovimiento(idMovimiento: any): Observable<any> {
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/retrieveInfoMovimiento/${idMovimiento}`), { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para lista paginada de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoMovimiento(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/pageListByInfoMovimiento'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para eliminar movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public deleteInfoMovimiento(idMovimiento: any): Observable<any> {
    return this.httpClient
      .delete<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/deleteInfoMovimiento/${idMovimiento}`), { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }
}
