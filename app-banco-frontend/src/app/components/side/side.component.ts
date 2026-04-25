import { Component } from '@angular/core';
import { Router } from '@angular/router';

/**
 * Componente de layout lateral
 *
 * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
 * @version 1.0 - Version Inicial
 * @since 22/04/2026
 */
@Component({
  selector: 'app-side',
  templateUrl: './side.component.html',
  styleUrls: ['./side.component.css']
})
export class SideComponent {
  /**
   * Contructor para layout lateral
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  constructor(private router: Router) { }

  /**
   * Declaración de variables
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  menuVisible = true;
  botonMenuVisible = true;
  isSuperAdmin: boolean = false;

  /**
   * Iniciador de componente lateral
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  ngOnInit(): void {
  }

  /**
   * Metodo para mostrar menú
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  toggleBoton() {
    this.botonMenuVisible = false;
    this.menuVisible = true;
  }

  /**
   * Metodo para navegar al inicio
   *
   * @author Juan Maigua <mailto:maiguarizocarlos@gmail.com>
   * @version 1.0 - Version Inicial
   * @since 22/04/2026
   */
  navegarPagina() {
    this.router.navigate(['/']).then(() => {
      window.location.reload();
    });
  }
}

