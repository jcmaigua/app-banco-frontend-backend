import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ValidationService } from '../_services/validation';
import { ReporteService } from 'src/app/services/reporte.service';


/**
 * Componente para reportes
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css'],
})
export class ReportesComponent implements OnInit {

  /**
   * Contructor para reportes
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(
    public reporteService: ReporteService,
    private validationService: ValidationService
  ) { }

  /**
   * Declaración de variables
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  dataVigente: any = [];
  public fechaInicio: string = '';
  public fechaFin: string = '';
  public searchIdentificacion: string = '';
  pageSizeVigente: number = 10;
  pageNumberVigente: number = 0;
  totalVigente: number = 0;
  totalPagesVigente: number = 0;
  pageSizeOptionsVigente = [5, 10, 20, 50, 100];
  showDetailModal = false;
  detail: any = null;

  /**
   * Iniciador para reportes
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  ngOnInit(): void {
    this.reporte();
  }

  /**
   * Metodo para listar datos de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  reporte() {
    this.reporteService.reportePage(this.fechaInicio, this.fechaFin, this.searchIdentificacion, this.pageNumberVigente, this.pageSizeVigente).subscribe(
      (response: any) => {
        let res = response;
        if (response.status === 'OK' && response.payload) {
          res = response.payload;
          if (res.content && res.content.length > 0) {
            this.dataVigente = res.content;
            this.totalVigente = res.totalElements;
            this.totalPagesVigente = res.totalPages;
          } else {
            this.dataVigente = [];
            this.totalVigente = 0;
            this.totalPagesVigente = 1;
          }
        } else {
          Swal.fire('Error al obtener datos', response.message, 'error');
        }

      },
      (error) => {
        const errorMsg = this.validationService.getErrorMessage(error);
        Swal.fire('Error al obtener datos', errorMsg, 'error');
      },
    );
  }

  /**
   * Metodo para listar datos de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  reportePdf() {
    this.reporteService.reportePagePdf(this.fechaInicio, this.fechaFin, this.searchIdentificacion, this.pageNumberVigente, this.pageSizeVigente).subscribe(
      (response: any) => {
        if (response.status === 'OK' && response.payload) {
          const base64 = response.payload.base64;
          const nombreArchivo = response.payload.nombreArchivo;
          const byteCharacters = atob(base64);
          const byteNumbers = new Array(byteCharacters.length);
          for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
          }
          const byteArray = new Uint8Array(byteNumbers);
          const blob = new Blob([byteArray], { type: 'application/pdf' });
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = nombreArchivo || 'reporte.pdf';
          a.click();
          window.URL.revokeObjectURL(url);
        }

      },
      (error) => {
        const errorMsg = this.validationService.getErrorMessage(error);
        Swal.fire('Error al actualizar', errorMsg, 'error');
      },
    );
  }

  /**
   * Metodo para buscar datos de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  buscar() {
    this.pageNumberVigente = 0;
    this.reporte();
  }

  /**
   * Metodo para paginar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  handlePageVigente(page: number) {
    this.pageNumberVigente = page;
    this.reporte();
  }

  /**
   * Metodo para resetear datos de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  resetear() {
    this.fechaFin = '';
    this.fechaInicio = '';
    this.searchIdentificacion = '';
    this.pageNumberVigente = 0;
    this.pageSizeVigente = 10;
    this.reporte();
  }

  /**
   * Metodo para abrir modal de actualizar datos de reporte
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openRetrieveInfoReporte(persona: any): void {
    this.detail = persona;
    this.showDetailModal = true;
  }
}
