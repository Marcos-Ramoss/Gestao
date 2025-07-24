import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FeriadoService } from '../../../services/feriado.service';
import { FeriadoResponseDto } from '../../../dto/feriado-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FeriadoFormComponent } from './feriado-form.component';

@Component({
  selector: 'app-feriado-list',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, NgIf, DialogModule, ToastModule, FeriadoFormComponent],
  providers: [MessageService],
  templateUrl: './feriado-list.component.html'
})
export class FeriadoListComponent implements OnInit {
  feriados: FeriadoResponseDto[] = [];
  carregando: boolean = true;
  erro: string | null = null;
  feriadoSelecionado: FeriadoResponseDto | null = null;
  exibirModalEdicao = false;
  exibirModalExclusao = false;

  constructor(private feriadoService: FeriadoService, private router: Router, private messageService: MessageService) {}

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
    const feriado = this.feriados.find(f => f.id === id);
    if (feriado) {
      this.feriadoSelecionado = { ...feriado };
      this.exibirModalEdicao = true;
    }
  }

  aoSalvarEdicao() {
    this.exibirModalEdicao = false;
    this.feriadoSelecionado = null;
    this.carregarFeriados();
  }

  deletarFeriado(id: number) {
    const feriado = this.feriados.find(f => f.id === id);
    if (feriado) {
      this.feriadoSelecionado = feriado;
      this.exibirModalExclusao = true;
    }
  }

  confirmarExclusao() {
    if (!this.feriadoSelecionado) return;
    this.feriadoService.deletarFeriado(this.feriadoSelecionado.id).subscribe({
      next: () => {
        this.messageService.add({severity:'success', summary:'Sucesso', detail:'Feriado excluído com sucesso!'});
        this.carregarFeriados();
        this.exibirModalExclusao = false;
        this.feriadoSelecionado = null;
      },
      error: () => {
        this.messageService.add({severity:'error', summary:'Erro', detail:'Erro ao excluir feriado.'});
      }
    });
  }

  cancelarExclusao() {
    this.exibirModalExclusao = false;
    this.feriadoSelecionado = null;
  }

  novoFeriado() {
    this.router.navigate(['/app/feriados/novo']);
  }
}