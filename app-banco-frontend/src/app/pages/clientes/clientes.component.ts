import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { GenericRequestDTO } from '../_dto/generic-request-DTO';
import { PersonaClienteUnifiedReqDTO } from '../_dto/persona-cliente-unified-req-DTO';
import { PageDTO } from '../_dto/page-DTO';
import { ValidationService } from '../_services/validation';
import { BusquedaGlobalDTO } from '../_dto/busqueda-global-DTO';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { ClienteReqDTO } from '../_dto/cliente-req-DTO';

/**
 * Componente para clientes
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css'],
})
export class ClientesComponent implements OnInit {
  /**
   * Contructor para clientes
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(
    public userService: UserService,
    private validationService: ValidationService
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
  showPasswordModal = false;
  showDetailModal = false;
  detailPersona: any = null;

  confirmarContrasena: string = '';
  hidePass = true;
  hidePassConfirm = true;
  hidePassAntigua = true;
  contrasenaNueva: string = '';
  contrasenaAntigua: string = '';

  saveUser: PersonaClienteUnifiedReqDTO = this.newPersona();
  updateUser: PersonaClienteUnifiedReqDTO = this.newPersona();
  passwordUser: ClienteReqDTO = { contrasena: '' };
  passwordTargetId = 0;

  /**
   * Iniciador para clientes
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  ngOnInit(): void {
    this.busquedaDinamica();
  }

  /**
   * Metodo para inicializar estructura de persona
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  private newPersona(): PersonaClienteUnifiedReqDTO {
    return {
      idPersona: 0,
      idCliente: 0,
      contrasena: '',
      identificacion: '',
      nombre: '',
      apellido: '',
      direccion: '',
      telefono: '',
      edad: null,
      genero: '',
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
        this.pageListByInfoCliente();
      });
    this.pageListByInfoCliente();
  }

  /**
   * Metodo para listar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  pageListByInfoCliente() {
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
    this.userService.pageListByInfoCliente(genericRequestDTO).subscribe(
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
   * Metodo para eliminar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  deleteInfoCliente(id: any) {
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
        this.userService.deleteInfoCliente(id).subscribe(
          (response: any) => {
            Swal.fire('Eliminado', 'Se ha eliminado el registro', 'success');

            this.pageListByInfoCliente();
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
   * Metodo para paginar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  handlePageVigente(nextPage: number) {
    if (nextPage < 0) return;
    this.pageNumberVigente = nextPage;
    this.pageListByInfoCliente();
  }

  /**
   * Metodo para abrir modal de guardar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openSaveInfoCliente() {
    this.saveUser = this.newPersona();
    this.confirmarContrasena = '';
    this.showSaveModal = true;
  }

  /**
   * Metodo para abrir modal de actualizar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openUpdateInfoCliente(id: any) {
    this.userService.retrieveInfoCliente(id).subscribe(
      (response: any) => {
        const res = response.status === 'OK' && response.payload ? response.payload : response;
        this.updateUser = { ...res };
        this.showUpdateModal = true;
      },
      (error) => Swal.fire('Error al actualizar', this.validationService.getErrorMessage(error), 'error'),
    );
  }

  /**
   * Metodo para abrir modal de actualizar contraseña de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openUpdateInfoContrasena(id: any) {
    this.userService.retrieveInfoCliente(id).subscribe(
      (response: any) => {
        const res = response.status === 'OK' && response.payload ? response.payload : response;
        this.passwordUser = { contrasena: res.contrasena };
        this.passwordTargetId = id;
        this.contrasenaAntigua = '';
        this.contrasenaNueva = '';
        this.confirmarContrasena = '';
        this.showPasswordModal = true;
      },
      (error) => Swal.fire('Error al actualizar', this.validationService.getErrorMessage(error), 'error'),
    );
  }

  /**
   * Metodo para abrir modal de detalle de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  openRetrieveInfoCliente(persona: any) {
    this.detailPersona = persona;
    this.showDetailModal = true;
  }

  /**
   * Metodo para guardar clientes
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitSave() {
    if (!this.validationService.validarContrasena(this.saveUser.contrasena)) return;
    if (!this.validationService.validarConfirmarContrasena(this.confirmarContrasena)) return;
    if (!this.validationService.validarCompararContrasena(this.saveUser.contrasena, this.confirmarContrasena)) return;
    if (!this.validationService.validarEdad(this.saveUser.edad)) return;
    const genericRequestDTO: GenericRequestDTO<PersonaClienteUnifiedReqDTO> = { userRequest: 'juan', payload: this.saveUser };
    this.userService.saveInfoPersona(genericRequestDTO).subscribe(
      () => {
        Swal.fire('Registro guardado', 'Registrado en el sistema', 'success');
        this.showSaveModal = false;
        this.pageListByInfoCliente();
      },
      (error) => Swal.fire('Error al guardar', this.validationService.getErrorMessage(error), 'error'),
    );
  }

  /**
   * Metodo para actualizar contraseña de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitPassword() {
    if (!this.validationService.validarContrasenaAntigua(this.contrasenaAntigua)) {
      return;
    }

    if (!this.validationService.validarContrasenaNueva(this.contrasenaNueva)) {
      return;
    }

    if (!this.validationService.validarConfirmarContrasena(this.confirmarContrasena)) {
      return;
    }

    if (!this.validationService.validarCompararContrasenaAntigua(this.passwordUser.contrasena, this.contrasenaAntigua)) {
      return;
    }

    if (!this.validationService.validarCompararContrasena(this.contrasenaNueva, this.confirmarContrasena)) {
      return;
    }

    this.passwordUser.contrasena = this.contrasenaNueva;
    const genericRequestDTO: GenericRequestDTO<ClienteReqDTO> = { userRequest: 'juan', payload: this.passwordUser };
    this.userService.updateContrasenia(this.passwordTargetId, genericRequestDTO).subscribe(
      () => {
        Swal.fire('Registro guardado', 'Registrado en el sistema', 'success');
        this.showPasswordModal = false;
      },
      (error) => Swal.fire('Error al guardar', this.validationService.getErrorMessage(error), 'error'),
    );
  }
  /**
   * Metodo para actualizar datos de cliente
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  submitUpdate() {
    const genericRequestDTO: GenericRequestDTO<PersonaClienteUnifiedReqDTO> = { userRequest: 'juan', payload: this.updateUser };
    this.userService.updateInfoPersona(genericRequestDTO).subscribe(
      () => {
        Swal.fire('Actualizado', 'Registro actualizado con éxito', 'success');
        this.showUpdateModal = false;
        this.pageListByInfoCliente();
      },
      (error) => Swal.fire('Error al actualizar', this.validationService.getErrorMessage(error), 'error'),
    );
  }
}
