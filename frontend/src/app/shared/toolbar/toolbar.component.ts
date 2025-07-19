import { Component, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { MenuModule } from 'primeng/menu';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-toolbar',
  standalone: true,
  imports: [MenuModule, ButtonModule, CommonModule],
  template: `
    <div class="w-full flex items-center p-4 bg-white shadow">
      <button pButton type="button" icon="pi pi-bars" class="p-button-rounded p-button-text text-2xl mr-4" (click)="onMenuToggle()"></button>
      <div class="flex-1"></div>
      <p-menu #userMenu [popup]="true" [model]="items"></p-menu>
      <button pButton type="button" icon="pi pi-user" class="p-button-rounded p-button-text text-2xl" (click)="userMenu.toggle($event)"></button>
    </div>
  `
})
export class ToolbarComponent {
  @Output() menuToggle = new EventEmitter<void>();
  items: MenuItem[] = [];

  constructor(private router: Router) {
    this.items = [
      {
        label: 'Perfil',
        icon: 'pi pi-user',
        command: () => this.goToProfile()
      },
      {
        label: 'Logout',
        icon: 'pi pi-sign-out',
        command: () => this.logout()
      }
    ];
  }

  onMenuToggle() {
    this.menuToggle.emit();
  }

  goToProfile() {
    this.router.navigate(['/app/perfil']);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
} 