import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { GenericRequestDTO } from '../_dto/generic-request-DTO';
import { PageDTO } from '../_dto/page-DTO';
import { ValidationService } from '../_services/validation';
import { MovimientoService } from 'src/app/services/movimiento.service';
import { BusquedaGlobalDTO } from '../_dto/busqueda-global-DTO';
import { MovimientoReqDTO } from '../_dto/movimiento-req-DTO';
import { CuentaService } from 'src/app/services/cuenta.service';
import { TipoMovimientoService } from 'src/app/services/tipo-movimiento.service';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

/**
 * Declaracion de interfaces y variables globales
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
@Component({
  selector: 'app-movimientos',
  templateUrl: './movimientos.component.html',
  styleUrls: ['./movimientos.component.css'],
})
export class MovimientosComponent implements OnInit {

  /**
   * Contructor para movimientos
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(
    public movimientoService: MovimientoService,
    private validationService: ValidationService,
    private cuentaService: CuentaService,
    private tipoMovimientoService: TipoMovimientoService
  ) { }

  /**
   * Declaración de variables
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageSizeVigente: number = 10;
  pageNumberVigente: number = 0;
  totalVigente: number = 0;
  totalPagesVigente: number = 0;
  dataVigente: any = [];
  public search: string = '';
  pageSizeOptionsVigente = [5, 10, 20, 50, 100];
  searchControl = new FormControl('');
  showSaveModal = false;
  showUpdateModal = false;
  showDetailModal = false;
  detail: any = null;
  tiposMovimiento: any[] = [];
  cuentas: any[] = [];
  filtroNumeroCuenta = '';
  saveMovimiento: MovimientoReqDTO = this.newMov();
  updateMovimiento: MovimientoReqDTO = this.newMov();

  /**
   * Iniciador para movimientos
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  ngOnInit(): void {
    this.busquedaDinamica();
    this.cargarTipos();
  }
  /**
   * Metodo para inicializar estructura de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private newMov(): MovimientoReqDTO {
    return { idMovimiento: 0, tipoMovimientoId: 0, tipoMovimientoNombre: '', tipoCuentaNombre: '', cuentaId: 0, numeroCuenta: '', valor: null };
  }

  /**
   * Metodo para la busqueda dinamica
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  busquedaDinamica() {
    this.searchControl.valueChanges
      .pipe(
        debounceTime(300),
        distinctUntilChanged()
      )
      .subscribe((valor: string) => {
        this.search = valor || '';
        this.pageNumberVigente = 0;
        this.pageListByInfoMovimiento();
      });
    this.pageListByInfoMovimiento();
  }

  /**
   * Metodo para listar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoMovimiento() {
    let param: BusquedaGlobalDTO = {
      valor: this.search || ''
    };

    let pageDTO: PageDTO<BusquedaGlobalDTO> = {
      page: this.pageNumberVigente,
      size: this.pageSizeVigente,
      tabla: param,
    };
    let genericRequestDTO: GenericRequestDTO<
      PageDTO<BusquedaGlobalDTO>
    > = {
      userRequest: 'juan',
      payload: pageDTO,
    };
    this.movimientoService.pageListByInfoMovimiento(genericRequestDTO).subscribe(
      (response: any) => {
        let res = response;
        if (response.status === 'OK' && response.payload) {
          res = response.payload;
        }
        if (res.content && res.content.length > 0) {
          this.dataVigente = res.content;
          this.totalVigente = res.totalElements;
          this.totalPagesVigente = res.totalPages;
        } else {
          this.dataVigente = [];
          this.totalVigente = 0;
          this.totalPagesVigente = 1;
        }
      },
      (error) => {
        const errorMsg = this.validationService.getErrorMessage(error);
        Swal.fire('Error al actualizar', errorMsg, 'error');
      },
    );
  }

  /**
   * Metodo para eliminar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  deleteInfoMovimiento(id: any) {
    Swal.fire({
      title: 'Eliminar',
      text: '¿Estás seguro de eliminar?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.movimientoService.deleteInfoMovimiento(id).subscribe(
          (response: any) => {
            Swal.fire('Eliminado', 'Se ha eliminado el registro', 'success');

            this.pageListByInfoMovimiento();
          },
          (error) => {
            const errorMsg = this.validationService.getErrorMessage(error);
            Swal.fire('Error al actualizar', errorMsg, 'error');
          },
        );
      }
    });
  }

  /**
   * Metodo para paginar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  handlePageVigente(page: number) {
    this.pageNumberVigente = page;
    this.pageListByInfoMovimiento();
  }

  /**
   * Metodo para abrir modal de guardar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openSaveInfoMovimiento(): void {
    this.saveMovimiento = this.newMov();
    this.showSaveModal = true;
  }

  /**
   * Metodo para abrir modal de actualizar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openUpdateInfoMovimiento(id: any): void {
    this.movimientoService.retrieveInfoMovimiento(id).subscribe((response: any) => {
      const res = response.status === 'OK' && response.payload ? response.payload : response;
      this.updateMovimiento = { ...this.newMov(), ...res };
      this.filtroNumeroCuenta = this.updateMovimiento.numeroCuenta;
      this.buscarCuenta();
      this.showUpdateModal = true;
    });

  }

  /**
   * Metodo para abrir modal de actualizar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openRetrieveInfoMovimiento(persona: any): void {
    this.detail = persona;
    this.showDetailModal = true;
  }

  /**
   * Metodo para limpiar los datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  limpiar() {
    this.saveMovimiento = this.newMov();
    this.updateMovimiento = this.newMov();
    this.filtroNumeroCuenta = '';
    this.cuentas = [];
  }

  /**
   * Metodo para cargar tipo de movimientos
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  cargarTipos() {
    this.tipoMovimientoService.listByAdmiTipoMovimiento('ACTIVO').subscribe((r: any) => (this.tiposMovimiento = r.payload || []));
  }

  /**
   * Metodo para buscar cuenta por número
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  buscarCuenta() {
    if (!this.filtroNumeroCuenta) return;
    this.cuentaService.listByInfoCuenta('ACTIVO', this.filtroNumeroCuenta).subscribe((response: any) => {
      this.cuentas = response.payload || [];
      if (this.cuentas.length > 0) {
        this.saveMovimiento.numeroCuenta = this.cuentas[0].numeroCuenta;
        this.updateMovimiento.numeroCuenta = this.cuentas[0].numeroCuenta;
      } else {
        Swal.fire('No se encontró cuenta', 'No se encontró ninguna cuenta con el número proporcionado', 'error');
        this.saveMovimiento.numeroCuenta = '';
        this.updateMovimiento.numeroCuenta = '';
      }
    });
  }

  /**
   * Metodo para guardar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitSave() {
    const req: GenericRequestDTO<MovimientoReqDTO> = { userRequest: 'juan', payload: this.saveMovimiento };
    this.movimientoService.saveInfoMovimiento(req).subscribe(() => {
      this.showSaveModal = false;
      this.limpiar();
      this.pageListByInfoMovimiento();
      Swal.fire('Registro guardado', 'Registrado en el sistema', 'success');
    }, (error) => {
      const errorMsg = this.validationService.getErrorMessage(error);
      Swal.fire('Error al guardar', errorMsg, 'error');
    });
  }
  /**
   * Metodo para actualizar datos de movimiento
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitUpdate() {
    const req: GenericRequestDTO<MovimientoReqDTO> = { userRequest: 'juan', payload: this.updateMovimiento };
    this.movimientoService.updateInfoMovimiento(req).subscribe(() => {
      this.showUpdateModal = false;
      this.limpiar();
      this.pageListByInfoMovimiento();
      Swal.fire('Actualizado', 'Registro actualizado con éxito', 'success');
    }, (error) => {
      const errorMsg = this.validationService.getErrorMessage(error);
      Swal.fire('Error al actualizar', errorMsg, 'error');
    });
  }
}
