import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FeriadoService } from '../../../services/feriado.service';
import { FeriadoResponseDto } from '../../../dto/feriado-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feriado-list',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, NgIf],
  templateUrl: './feriado-list.component.html'
})
export class FeriadoListComponent implements OnInit {
  feriados: FeriadoResponseDto[] = [];
  carregando: boolean = true;
  erro: string | null = null;

  constructor(private feriadoService: FeriadoService, private router: Router) {}

  ngOnInit() {
    this.carregarFeriados();
  }

  carregarFeriados() {
    this.carregando = true;
    this.feriadoService.listarFeriados().subscribe({
      next: (dados) => {
        this.feriados = dados;
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Erro ao carregar feriados.';
        this.carregando = false;
      }
    });
  }

  editarFeriado(id: number) {
    // Modal de edição será implementado depois
  }

  deletarFeriado(id: number) {
    // Modal de confirmação será implementado depois
  }

  novoFeriado() {
    this.router.navigate(['/app/feriados/novo']);
  }
} 