import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrdemServicoService } from '../../../services/ordem-servico.service';
import { OrdemServicoRequestDto } from '../../../dto/ordem-servico-request.dto';
import { OrdemServicoResponseDto } from '../../../dto/ordem-servico-response.dto';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { AreaService } from '../../../services/area.service';
import { AreaResponseDto } from '../../../dto/area-response.dto';

@Component({
  selector: 'app-ordem-servico-form',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, ButtonModule, DropdownModule, ToastModule],
  providers: [MessageService],
  templateUrl: './ordem-servico-form.component.html',
})
export class OrdemServicoFormComponent implements OnInit {
  @Input() ordemServico: OrdemServicoResponseDto | null = null;
  @Output() salvo = new EventEmitter<void>();
  @Output() cancelado = new EventEmitter<void>();

  formData: OrdemServicoRequestDto = {
    areaId: 0,
    numeroOs: ''
  };

  areas: AreaResponseDto[] = [];
  carregando: boolean = false;
  erro: string | null = null;
  editando: boolean = false;

  constructor(
    private ordemServicoService: OrdemServicoService,
    private areaService: AreaService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.carregarAreas();
    this.inicializarFormulario();
  }

  carregarAreas() {
    this.areaService.listarAreas().subscribe({
      next: (areas) => {
        this.areas = areas;
      },
      error: () => {
        this.erro = 'Erro ao carregar áreas.';
      }
    });
  }

  inicializarFormulario() {
    if (this.ordemServico) {
      this.editando = true;
      this.formData = {
        areaId: this.ordemServico.areaId,
        numeroOs: this.ordemServico.numeroOs
      };
    }
  }

  salvar() {
    if (!this.validarFormulario()) {
      return;
    }

    this.carregando = true;
    this.erro = null;

    const operacao = this.editando
      ? this.ordemServicoService.atualizarOrdemServico(this.ordemServico!.id, this.formData)
      : this.ordemServicoService.criarOrdemServico(this.formData);

    operacao.subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Sucesso',
          detail: this.editando 
            ? 'Ordem de serviço atualizada com sucesso!' 
            : 'Ordem de serviço criada com sucesso!'
        });
        this.carregando = false;
        // Pequeno delay para mostrar a mensagem antes de fechar o modal
        setTimeout(() => {
          this.salvo.emit();
        }, 1000);
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro',
          detail: this.editando 
            ? 'Erro ao atualizar ordem de serviço' 
            : 'Erro ao criar ordem de serviço'
        });
        this.carregando = false;
        this.erro = this.editando 
          ? 'Erro ao atualizar ordem de serviço.' 
          : 'Erro ao criar ordem de serviço.';
      }
    });
  }

  cancelar() {
    this.cancelado.emit();
  }

  validarFormulario(): boolean {
    if (!this.formData.areaId) {
      this.erro = 'Área é obrigatória.';
      return false;
    }

    if (!this.formData.numeroOs?.trim()) {
      this.erro = 'Número da OS é obrigatório.';
      return false;
    }

    this.erro = null;
    return true;
  }

  limparErro() {
    this.erro = null;
  }
} 