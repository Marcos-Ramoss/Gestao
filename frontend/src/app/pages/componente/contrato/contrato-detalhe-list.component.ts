import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContratoDetalheService } from '../../../services/contrato-detalhe.service';
import { ContratoDetalheResponseDto } from '../../../dto/contrato-detalhe-response.dto';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { NgIf, NgFor } from '@angular/common';
import { ContratoDetalheFormComponent } from './contrato-detalhe-form.component';

@Component({
  selector: 'app-contrato-detalhe-list',
  standalone: true,
  imports: [CommonModule, ButtonModule, DialogModule, ToastModule, NgIf, NgFor, ContratoDetalheFormComponent],
  providers: [MessageService],
  templateUrl: './contrato-detalhe-list.component.html'
})
export class ContratoDetalheListComponent implements OnChanges {
  @Input() codigoContrato: string = '';
  detalhes: ContratoDetalheResponseDto[] = [];
  detalheSelecionado: ContratoDetalheResponseDto | null = null;
  mostrarModal = false;
  mostrarForm = false;
  carregando = false;

  constructor(private service: ContratoDetalheService, private messageService: MessageService) {}

  ngOnChanges(changes: SimpleChanges) {
    console.log('[ContratoDetalheList] ngOnChanges', changes, 'codigoContrato:', this.codigoContrato);
    if (changes['codigoContrato'] && this.codigoContrato) {
      this.carregarDetalhes();
    }
  }

  carregarDetalhes() {
    console.log('[ContratoDetalheList] carregarDetalhes para codigoContrato:', this.codigoContrato);
    this.carregando = true;
    this.service.listarContratoDetalhes().subscribe({
      next: (dados) => {
        console.log('[ContratoDetalheList] Dados recebidos:', dados);
        this.detalhes = dados.filter(d => d.codigoContrato === this.codigoContrato);
        console.log('[ContratoDetalheList] Detalhes filtrados:', this.detalhes);
        this.carregando = false;
      },
      error: (err) => {
        this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao carregar detalhes.' });
        this.carregando = false;
        console.error('[ContratoDetalheList] Erro ao carregar detalhes:', err);
      }
    });
  }

  novoDetalhe() {
    this.detalheSelecionado = null;
    this.mostrarForm = true;
  }

  editarDetalhe(detalhe: ContratoDetalheResponseDto) {
    this.detalheSelecionado = detalhe;
    this.mostrarForm = true;
  }

  fecharForm(atualizar: boolean) {
    this.mostrarForm = false;
    this.detalheSelecionado = null;
    if (atualizar) this.carregarDetalhes();
  }

  deletarDetalhe(detalhe: ContratoDetalheResponseDto) {
    if (confirm('Deseja realmente excluir este detalhe?')) {
      this.service.deletarContratoDetalhe(detalhe.id).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Detalhe excluído com sucesso!' });
          this.carregarDetalhes();
        },
        error: () => {
          this.messageService.add({ severity: 'error', summary: 'Erro', detail: 'Erro ao excluir detalhe.' });
        }
      });
    }
  }
} 