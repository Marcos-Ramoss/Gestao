import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './welcome.component.html'
})
export class WelcomeComponent implements OnInit {
  nomeUsuario: string = '';

  ngOnInit() {
    this.nomeUsuario = localStorage.getItem('nomeUsuario') || 'Usuário';
  }
} 