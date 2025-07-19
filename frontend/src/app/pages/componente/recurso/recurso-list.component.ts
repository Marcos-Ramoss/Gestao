import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecursoService } from '../../../services/recurso.service';
import { RecursoResponseDto } from '../../../dto/recurso-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { DialogModule } from 'primeng/dialog';
import { RecursoFormComponent } from './recurso-form.component';

@Component({
  selector: 'app-recurso-list',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, NgIf, DialogModule, RecursoFormComponent],
  templateUrl: './recurso-list.component.html'
})
export class RecursoListComponent implements OnInit {
  recursos: RecursoResponseDto[] = [];
  carregando: boolean = true;
  erro: string | null = null;
  exibirModalEdicao = false;
  exibirModalExclusao = false;
  recursoSelecionado?: RecursoResponseDto;

  constructor(private recursoService: RecursoService, private router: Router) {}

  ngOnInit() {
    this.carregarRecursos();
  }

  carregarRecursos() {
    this.carregando = true;
    this.recursoService.listarRecursos().subscribe({
      next: (dados) => {
        this.recursos = dados;
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Erro ao carregar recursos.';
        this.carregando = false;
      }
    });
  }

  editarRecurso(id: number) {
    this.recursoSelecionado = this.recursos.find(r => r.id === id);
    this.exibirModalEdicao = true;
  }

  aoSalvarEdicao() {
    this.exibirModalEdicao = false;
    this.carregarRecursos();
  }

  deletarRecurso(id: number) {
    this.recursoSelecionado = this.recursos.find(r => r.id === id);
    this.exibirModalExclusao = true;
  }

  confirmarExclusao() {
    if (this.recursoSelecionado) {
      this.recursoService.deletarRecurso(this.recursoSelecionado.id).subscribe({
        next: () => {
          this.exibirModalExclusao = false;
          this.carregarRecursos();
        },
        error: () => {
          this.erro = 'Erro ao excluir recurso.';
          this.exibirModalExclusao = false;
        }
      });
    }
  }

  cancelarExclusao() {
    this.exibirModalExclusao = false;
  }

  novoRecurso() {
    this.router.navigate(['/app/recursos/novo']);
  }
} 