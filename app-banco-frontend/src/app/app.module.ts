import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxUiLoaderModule, NgxUiLoaderHttpModule } from "ngx-ui-loader";
import { PaginatePipe } from './pipes/paginate.pipe';
import { ClientesComponent } from './pages/clientes/clientes.component';
import { SideComponent } from './components/side/side.component';
import { CuentasComponent } from './pages/cuentas/cuentas.component';
import { MovimientosComponent } from './pages/movimientos/movimientos.component';
import { ReportesComponent } from './pages/reportes/reportes.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesComponent,
    PaginatePipe,
    SideComponent,
    CuentasComponent,
    MovimientosComponent,
    ReportesComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule.forRoot({
      showForeground: true
    }),
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
