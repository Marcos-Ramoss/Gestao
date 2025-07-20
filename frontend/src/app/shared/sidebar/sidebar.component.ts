import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent {
  model: MenuItem[] = [];

  constructor(private router: Router) {}

  ngOnInit() {
    this.model = [
      {
        label: 'Upload',
        icon: 'pi pi-upload'
      }
    ];
  }

  goToUpload() {
    this.router.navigate(['/app/faturamento']);
  }

  goToUsuarios() {
    this.router.navigate(['/app/usuarios']);
  }

  goToColaborador() {
    this.router.navigate(['/app/recursos']);
  }

  goToFeriados() {
    this.router.navigate(['/app/feriados']);
  }

  goToAreas() {
    this.router.navigate(['/app/areas']);
  }

  goToWelcome() {
    this.router.navigate(['/app']);
  }

  goToContratos() {
    this.router.navigate(['/app/contratos']);
  }

  goToOrdensServico() {
    this.router.navigate(['/app/ordens-servico']);
  }
} 