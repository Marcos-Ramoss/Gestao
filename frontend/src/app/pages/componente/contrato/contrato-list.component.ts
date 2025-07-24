import { Component, OnInit } from '@angular/core';
import { ContratoService } from '../../../services/contrato.service';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { NgIf } from '@angular/common';
import { ContratoFormComponent } from './contrato-form.component';
import { ContratoDetalheListComponent } from './contrato-detalhe-list.component';
import { ContratoDetalheFormComponent } from './contrato-detalhe-form.component';

@Component({
  selector: 'app-contrato-list',
  templateUrl: './contrato-list.component.html',
  standalone: true,
  imports: [TableModule, ButtonModule, DialogModule, ToastModule, NgIf, ContratoFormComponent, ContratoDetalheListComponent, ContratoDetalheFormComponent],
  providers: [MessageService]
})
export class ContratoListComponent implements OnInit {
  contratos: ContratoResponseDto[] = [];
  displayForm = false;
  contratoSelecionado: ContratoResponseDto | null = null;
  displayDetalhes = false;
  contratoDetalheCodigo: string = '';
  displayNovoDetalhe = false;
  contratoParaNovoDetalhe: string = '';
  mostrarConfirmacaoExclusao = false;
  contratoParaExcluir: ContratoResponseDto | null = null;

  constructor(
    private contratoService: ContratoService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.carregarContratos();
  }

  carregarContratos() {
    this.contratoService.listarContratos().subscribe({
      next: (dados) => this.contratos = dados,
      error: () => this.contratos = []
    });
  }

  novoContrato() {
    this.contratoSelecionado = null;
    this.displayForm = true;
  }

  editarContrato(contrato: ContratoResponseDto) {
    this.contratoSelecionado = contrato;
    this.displayForm = true;
  }

  fecharForm(atualizar: boolean) {
    this.displayForm = false;
    this.contratoSelecionado = null;
    if (atualizar) {
      // Atualiza a lista local removendo o antigo e inserindo o novo, para refletir alteração de código
      this.carregarContratos();
    }
  }

  pedirConfirmacaoExclusao(contrato: ContratoResponseDto) {
    this.contratoParaExcluir = contrato;
    this.mostrarConfirmacaoExclusao = true;
  }
  confirmarExclusao() {
    if (!this.contratoParaExcluir) return;
    this.contratoService.deletarContrato(this.contratoParaExcluir.codigoContrato).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Sucesso',
          detail: 'Contrato excluído com sucesso!'
        });
        this.carregarContratos();
        this.mostrarConfirmacaoExclusao = false;
        this.contratoParaExcluir = null;
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro',
          detail: 'Erro ao excluir contrato'
        });
        this.mostrarConfirmacaoExclusao = false;
        this.contratoParaExcluir = null;
      }
    });
  }
  cancelarExclusao() {
    this.mostrarConfirmacaoExclusao = false;
    this.contratoParaExcluir = null;
  }

  detalharContrato(contrato: ContratoResponseDto) {
    this.contratoDetalheCodigo = contrato.codigoContrato;
    this.displayDetalhes = true;
  }

  fecharDetalhes() {
    this.displayDetalhes = false;
    this.contratoDetalheCodigo = '';
  }

  abrirNovoDetalhe(contrato: ContratoResponseDto) {
    this.contratoParaNovoDetalhe = contrato.codigoContrato;
    this.displayNovoDetalhe = true;
  }
  fecharNovoDetalhe(atualizar: boolean) {
    this.displayNovoDetalhe = false;
    this.contratoParaNovoDetalhe = '';
    if (atualizar && this.displayDetalhes && this.contratoDetalheCodigo) {
      // Se o modal de detalhes estiver aberto para o mesmo contrato, recarregar detalhes
      // Forçar recarregamento do componente de detalhes
      this.displayDetalhes = false;
      setTimeout(() => { this.displayDetalhes = true; }, 0);
    }
  }
} 