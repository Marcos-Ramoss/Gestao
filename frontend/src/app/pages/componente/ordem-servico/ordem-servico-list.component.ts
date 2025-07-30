import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdemServicoService } from '../../../services/ordem-servico.service';
import { OrdemServicoResponseDto } from '../../../dto/ordem-servico-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgIf } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { OrdemServicoFormComponent } from './ordem-servico-form.component';
import { TooltipModule } from 'primeng/tooltip';
import { AreaService } from '../../../services/area.service';
import { AreaResponseDto } from '../../../dto/area-response.dto';

interface OrdemServicoComArea extends OrdemServicoResponseDto {
  area?: AreaResponseDto;
}

@Component({
  selector: 'app-ordem-servico-list',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, NgIf, DialogModule, ToastModule, OrdemServicoFormComponent, TooltipModule],
  providers: [MessageService],
  templateUrl: './ordem-servico-list.component.html',
})
export class OrdemServicoListComponent implements OnInit {
  ordensServico: OrdemServicoComArea[] = [];
  carregando: boolean = true;
  erro: string | null = null;
  ordemServicoSelecionada: OrdemServicoResponseDto | null = null;
  mostrarModalEdicao = false;
  mostrarModalCriacao = false;
  mostrarConfirmacaoExclusao = false;
  idParaExcluir: number | null = null;

  constructor(
    private ordemServicoService: OrdemServicoService,
    private areaService: AreaService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.carregarOrdensServico();
  }

  carregarOrdensServico() {
    this.carregando = true;
    this.ordemServicoService.listarOrdensServico().subscribe({
      next: (dados) => {
        this.ordensServico = dados;
        this.carregarInformacoesAreas();
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Erro ao carregar ordens de serviço.';
        this.carregando = false;
      }
    });
  }

  carregarInformacoesAreas() {
    // Buscar informações das áreas para cada ordem de serviço
    this.ordensServico.forEach(ordemServico => {
      this.areaService.buscarAreaPorId(ordemServico.areaId).subscribe({
        next: (area) => {
          ordemServico.area = area;
        },
        error: () => {
          // Se não conseguir carregar a área, mantém apenas o ID
        }
      });
    });
  }

  editarOrdemServico(id: number) {
    const ordemServico = this.ordensServico.find(os => os.id === id);
    if (ordemServico) {
      this.ordemServicoSelecionada = { ...ordemServico };
      this.mostrarModalEdicao = true;
    }
  }

  fecharModalEdicao(atualizarLista: boolean = false) {
    this.mostrarModalEdicao = false;
    this.ordemServicoSelecionada = null;
    if (atualizarLista) {
      this.carregarOrdensServico();
    }
  }

  fecharModalCriacao(atualizarLista: boolean = false) {
    this.mostrarModalCriacao = false;
    this.ordemServicoSelecionada = null;
    if (atualizarLista) {
      this.carregarOrdensServico();
    }
  }

  deletarOrdemServico(id: number) {
    this.idParaExcluir = id;
    this.mostrarConfirmacaoExclusao = true;
  }

  confirmarExclusao() {
    if (this.idParaExcluir !== null) {
      this.ordemServicoService.deletarOrdemServico(this.idParaExcluir).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Sucesso',
            detail: 'Ordem de serviço excluída com sucesso!'
          });
          this.mostrarConfirmacaoExclusao = false;
          this.idParaExcluir = null;
          this.carregarOrdensServico();
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Erro ao excluir ordem de serviço'
          });
          this.mostrarConfirmacaoExclusao = false;
          this.idParaExcluir = null;
          this.erro = 'Erro ao excluir ordem de serviço.';
        }
      });
    }
  }

  cancelarExclusao() {
    this.mostrarConfirmacaoExclusao = false;
    this.idParaExcluir = null;
  }

  novaOrdemServico() {
    this.ordemServicoSelecionada = null;
    this.mostrarModalCriacao = true;
  }
} 