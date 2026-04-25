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
 * Clase para servicios de cliente
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
export class UserService {
  /**
   * Variables de los servicios de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private msAdminConfigMgmt = environment.msAdminConfig;
  private typeService = environment.typeService[0];
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  /**
   * Contructor del servicio de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Metodo para guardar cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  saveInfoPersona(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/saveInfoPersona'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para actualizar cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  updateInfoPersona(param: any) {
    return this.httpClient
      .put<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/updateInfoPersona'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para actualizar contraseña
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  updateContrasenia(idCliente: any, param: any) {
    return this.httpClient
      .patch<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/updateContrasenia/${idCliente}`), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }


  /**
   * Metodo para retornar cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public retrieveInfoCliente(idCliente: any): Observable<any> {
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/retrieveInfoCliente/${idCliente}`), { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para lista paginada de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoCliente(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/pageListByInfoCliente'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para eliminar cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public deleteInfoCliente(idCliente: any): Observable<any> {
    return this.httpClient
      .delete<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/deleteInfoCliente/${idCliente}`), { headers: this.httpHeaders })
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
  public listByInfoCliente(estado: any, identificacion: any): Observable<any> {
    const params = new HttpParams()
      .set('estado', estado.toString())
      .set('identificacion', identificacion.toString());
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/listByInfoCliente`), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }
}
