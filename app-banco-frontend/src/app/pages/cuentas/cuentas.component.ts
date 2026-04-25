import { Component, OnInit } from '@angular/core';
import { CuentaService } from 'src/app/services/cuenta.service';
import Swal from 'sweetalert2';
import { GenericRequestDTO } from '../_dto/generic-request-DTO';
import { PageDTO } from '../_dto/page-DTO';
import { ValidationService } from '../_services/validation';
import { BusquedaGlobalDTO } from '../_dto/busqueda-global-DTO';
import { TipoCuentaService } from 'src/app/services/tipo-cuenta.service';
import { CuentaPersonaUnifiedReqDTO } from '../_dto/cuenta-persona-unified-req-DTO';
import { UserService } from 'src/app/services/user.service';
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
  selector: 'app-cuentas',
  templateUrl: './cuentas.component.html',
  styleUrls: ['./cuentas.component.css'],
})
export class CuentasComponent implements OnInit {

  /**
   * Contructor para cuentas
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(
    public cuentaService: CuentaService,
    private validationService: ValidationService,
    private tipoCuentaService: TipoCuentaService,
    private userService: UserService
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
  tiposCuenta: any[] = [];
  usuarios: any[] = [];
  filtroIdentificacion = '';
  saveCuenta: CuentaPersonaUnifiedReqDTO = this.newCuenta();
  updateCuenta: CuentaPersonaUnifiedReqDTO = this.newCuenta();

  /**
   * Iniciador para cuentas
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  ngOnInit(): void {
    this.busquedaDinamica();
  }

  /**
   * Metodo para inicializar estructura de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private newCuenta(): CuentaPersonaUnifiedReqDTO {
    return {
      idCuenta: 0,
      tipoCuentaId: 0,
      personaId: 0,
      identificacion: '',
      nombre: '',
      apellido: '',
      saldoInicial: null,
      numeroCuenta: '',
      nombreTipoCuenta: ''
    };
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
        this.pageListByInfoCuenta();
      });
    this.pageListByInfoCuenta();
  }

  /**
   * Metodo para listar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoCuenta() {
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
    this.cuentaService.pageListByInfoCuenta(genericRequestDTO).subscribe(
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
   * Metodo para eliminar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  deleteInfoCuenta(id: any) {
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
        this.cuentaService.deleteInfoCuenta(id).subscribe(
          (response: any) => {
            Swal.fire('Eliminado', 'Se ha eliminado el registro', 'success');

            this.pageListByInfoCuenta();
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
   * Metodo para paginar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  handlePageVigente(page: number) {
    this.pageNumberVigente = page;
    this.pageListByInfoCuenta();
  }

  /**
   * Metodo para abrir modal de guardar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openSaveInfoCuenta(): void {
    this.cargarTiposCuenta();
    this.saveCuenta = this.newCuenta();
    this.usuarios = [];
    this.filtroIdentificacion = '';
    this.showSaveModal = true;
  }

  /**
   * Metodo para abrir modal de actualizar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openUpdateInfoCuenta(id: any): void {
    this.cargarTiposCuenta();
    this.cuentaService.retrieveInfoCuenta(id).subscribe((response: any) => {
      const res = response.status === 'OK' && response.payload ? response.payload : response;
      this.updateCuenta = { ...this.newCuenta(), ...res };
      this.filtroIdentificacion = this.updateCuenta.identificacion;
      this.buscarPersona();
      this.showUpdateModal = true;
    });
  }

  /**
   * Metodo para abrir modal de actualizar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openRetrieveInfoCuenta(persona: any): void {
    this.detail = persona;
    this.showDetailModal = true;
  }
  /**
   * Metodo para cargar tipo de cuentas
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  cargarTiposCuenta() {
    this.tipoCuentaService.listByAdmiTipoCuenta('ACTIVO').subscribe((r: any) => (this.tiposCuenta = r.payload || []));
  }

  /**
   * Metodo para buscar persona por identificacion
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  buscarPersona() {
    if (!this.filtroIdentificacion) return;
    this.userService.listByInfoCliente('ACTIVO', this.filtroIdentificacion).subscribe((response: any) => {
      this.usuarios = response.payload || [];
      if (this.usuarios.length > 0) {
        this.saveCuenta.personaId = this.usuarios[0].idPersona;
        this.updateCuenta.personaId = this.usuarios[0].idPersona;
      } else {
        Swal.fire('No se encontró cliente', 'No se encontró ningún cliente con la identificación proporcionada', 'error');
        this.saveCuenta.personaId = 0;
        this.updateCuenta.personaId = 0;
      }
    });
  }

  /**
   * Metodo para limpiar los datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  limpiar() {
    this.saveCuenta = this.newCuenta();
    this.updateCuenta = this.newCuenta();
    this.filtroIdentificacion = '';
    this.usuarios = [];
  }

  /**
   * Metodo para guardar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitSave() {
    const req: GenericRequestDTO<CuentaPersonaUnifiedReqDTO> = { userRequest: 'juan', payload: this.saveCuenta };
    this.cuentaService.saveInfoCuenta(req).subscribe(() => {
      this.showSaveModal = false;
      this.limpiar();
      this.pageListByInfoCuenta();
      Swal.fire('Registro guardado', 'Registrada en el sistema', 'success');
    }, (e) => Swal.fire('Error al guardar', this.validationService.getErrorMessage(e), 'error'));
  }
  /**
   * Metodo para actualizar datos de cuenta
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitUpdate() {
    const req: GenericRequestDTO<CuentaPersonaUnifiedReqDTO> = { userRequest: 'juan', payload: this.updateCuenta };
    this.cuentaService.updateInfoCuenta(req).subscribe(() => {
      this.showUpdateModal = false;
      this.limpiar();
      this.pageListByInfoCuenta();
      Swal.fire('Actualizado', 'Registro actualizado con éxito', 'success');
    }, (e) => Swal.fire('Error al actualizar', this.validationService.getErrorMessage(e), 'error'));
  }
}
