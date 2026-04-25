import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { catchError } from 'rxjs/operators';
import { GenericResponseDTO } from '../pages/_dto/generic-response-list-DTO';
@Injectable({
  providedIn: 'root',
})

/**
 * Clase para servicios de cuenta
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
export class CuentaService {
  /**
   * Variables de los servicios de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private msAdminConfigMgmt = environment.msAdminConfig;
  private typeService = environment.typeService[1];
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  /**
   * Contructor del servicio de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Metodo para guardar cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  saveInfoCuenta(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/saveInfoCuenta'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para actualizar cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  updateInfoCuenta(param: any) {
    return this.httpClient
      .put<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/updateInfoCuenta'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para retornar cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public retrieveInfoCuenta(idCuenta: any): Observable<any> {
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/retrieveInfoCuenta/${idCuenta}`), { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para lista paginada de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoCuenta(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/pageListByInfoCuenta'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para eliminar cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public deleteInfoCuenta(idCuenta: any): Observable<any> {
    return this.httpClient
      .delete<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/deleteInfoCuenta/${idCuenta}`), { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para obtener usuario
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public listByInfoCuenta(estado: any, numeroCuenta: any): Observable<any> {
    const params = new HttpParams()
      .set('estado', estado.toString())
      .set('numeroCuenta', numeroCuenta.toString());
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/listByInfoCuenta`), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }
}
