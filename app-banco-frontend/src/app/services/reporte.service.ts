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
 * Clase para servicios de reporte
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
export class ReporteService {
  /**
   * Variables de los servicios de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private msAdminConfigMgmt = environment.msAdminConfig;
  private typeService = environment.typeService[3];
  private httpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  /**
   * Contructor del servicio de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private httpClient: HttpClient) { }

  /**
   * Metodo para listar reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public reporte(fechaDesde: any, fechaHasta: any, idCliente: any): Observable<any> {
    const params = new HttpParams()
      .set('fechaDesde', fechaDesde.toString())
      .set('fechaHasta', fechaHasta.toString())
      .set('identificacion', idCliente.toString());
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para listar reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public reportePage(fechaDesde: any, fechaHasta: any, idCliente: any, page: any, size: any): Observable<any> {
    const params = new HttpParams()
      .set('fechaDesde', fechaDesde.toString())
      .set('fechaHasta', fechaHasta.toString())
      .set('identificacion', idCliente.toString())
      .set('page', page.toString())
      .set('size', size.toString());
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/page'), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }

  /**
   * Metodo para listar reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  public reportePagePdf(fechaDesde: any, fechaHasta: any, idCliente: any, page: any, size: any): Observable<any> {
    const params = new HttpParams()
      .set('fechaDesde', fechaDesde.toString())
      .set('fechaHasta', fechaHasta.toString())
      .set('identificacion', idCliente.toString())
      .set('page', page.toString())
      .set('size', size.toString());
    return this.httpClient
      .get<
        GenericResponseDTO<any>
      >(this.msAdminConfigMgmt.concat(this.typeService + '/pagePdf'), { params, headers: this.httpHeaders })
      .pipe(
        catchError((err) => {
          console.log('error: ' + err);
          return throwError(err);
        }),
      );
  }
}
