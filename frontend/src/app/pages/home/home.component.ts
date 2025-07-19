import { Component } from '@angular/core';
import { DrawerModule } from 'primeng/drawer';
import { ButtonModule } from 'primeng/button';
import { ToolbarComponent } from '../../shared/toolbar/toolbar.component';
import { SidebarComponent } from '../../shared/sidebar/sidebar.component';
import { NgIf } from '@angular/common';
import { RegisterFormComponent } from '../componente/usuario/register-form.component';
import { ActivatedRoute, Router } from '@angular/router';
import { PerfilComponent } from '../componente/usuario/perfil.component';
import { WelcomeComponent } from '../componente/dashboard/welcome.component';
import { UploadFormComponent } from '../componente/upload/upload-form.component';
import { RecursoListComponent } from '../componente/recurso/recurso-list.component';
import { RecursoFormComponent } from '../componente/recurso/recurso-form.component';
import { FeriadoListComponent } from '../componente/feriado/feriado-list.component';
import { FeriadoFormComponent } from '../componente/feriado/feriado-form.component';
import { AreaListComponent } from '../componente/area/area-list.component';
import { AreaFormComponent } from '../componente/area/area-form.component';
import { ContratoListComponent } from '../componente/contrato/contrato-list.component';
import { ContratoFormComponent } from "../componente/contrato/contrato-form.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  standalone: true,
  imports: [DrawerModule, ButtonModule, ToolbarComponent, SidebarComponent, NgIf, RegisterFormComponent, PerfilComponent, WelcomeComponent, UploadFormComponent, RecursoListComponent, RecursoFormComponent, FeriadoListComponent, FeriadoFormComponent, AreaListComponent, AreaFormComponent, ContratoListComponent, ContratoFormComponent]
})
export class HomeComponent {
  visible: boolean = true; // Sidebar aberta por padrão
  selected: 'login' | 'upload' = 'login';
  selectedPage: 'upload' | 'usuarios' | 'perfil' | 'faturamento' | 'recursos' | 'recursosNovo' | 'recursosEditar' | 'feriados' | 'feriadosNovo' | 'feriadosEditar' | 'areas' | 'areasNovo' | 'areasEditar' | 'contratos' | 'contratosNovo' | 'contratosEditar' = 'upload';

  constructor(private router: Router, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.url.subscribe(() => {
      if (this.router.url.endsWith('/usuarios')) {
        this.selectedPage = 'usuarios';
      } else if (this.router.url.endsWith('/perfil')) {
        this.selectedPage = 'perfil';
      } else if (this.router.url.endsWith('/faturamento')) {
        this.selectedPage = 'faturamento';
      } else if (this.router.url.endsWith('/recursos/novo')) {
        this.selectedPage = 'recursosNovo';
      } else if (/\/recursos\/\d+\/editar$/.test(this.router.url)) {
        this.selectedPage = 'recursosEditar';
      } else if (this.router.url.endsWith('/recursos')) {
        this.selectedPage = 'recursos';
      } else if (this.router.url.endsWith('/feriados/novo')) {
        this.selectedPage = 'feriadosNovo';
      } else if (/\/feriados\/\d+\/editar$/.test(this.router.url)) {
        this.selectedPage = 'feriadosEditar';
      } else if (this.router.url.endsWith('/feriados')) {
        this.selectedPage = 'feriados';
      } else if (this.router.url.endsWith('/areas/novo')) {
        this.selectedPage = 'areasNovo';
      } else if (/\/areas\/\d+\/editar$/.test(this.router.url)) {
        this.selectedPage = 'areasEditar';
      } else if (this.router.url.endsWith('/areas')) {
        this.selectedPage = 'areas';
      } else if (this.router.url.endsWith('/contratos/novo')) {
        this.selectedPage = 'contratosNovo';
      } else if (/\/contratos\/[^/]+\/editar$/.test(this.router.url)) {
        this.selectedPage = 'contratosEditar';
      } else if (this.router.url.endsWith('/contratos')) {
        this.selectedPage = 'contratos';
      } else {
        this.selectedPage = 'upload';
      }
    });
  }

  onSelect(option: 'login' | 'upload') {
    this.selected = option;
  }

  onSelectPage(page: 'upload' | 'usuarios') {
    this.selectedPage = page;
  }

  toggleSidebar() {
    this.visible = !this.visible;
  }

  onLogin(data: {usuario: string, senha: string}) {
    // Lógica de login futura
    console.log('Login:', data);
  }

  onUpload(data: {ano: number, mes: number, files: File[]}) {
    // Lógica de upload futura
    console.log('Upload:', data);
  }
} 