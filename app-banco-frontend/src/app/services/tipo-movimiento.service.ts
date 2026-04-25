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
 * Clase para servicios de tipo movimiento
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
export class TipoMovimientoService {

  /**
   * Variables de los servicios de tipo movimiento
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
   * Contructor del servicio de tipo movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Metodo para lista paginada de tipo movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByAdmiTipoMovimiento(param: any) {
    return this.httpClient
      .post<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/pageListByAdmiTipoMovimiento'), param, { headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para eliminar tipo movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public listByAdmiTipoMovimiento(estado: any): Observable<any> {
    const params = new HttpParams()
      .set('estado', estado.toString())
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + `/listByAdmiTipoMovimiento`), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }
}
