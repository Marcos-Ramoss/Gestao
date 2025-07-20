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
import { ContratoService } from '../../../services/contrato.service';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';

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
    codigoContrato: '',
    numeroOs: ''
  };

  contratos: ContratoResponseDto[] = [];
  carregando: boolean = false;
  erro: string | null = null;
  editando: boolean = false;

  constructor(
    private ordemServicoService: OrdemServicoService,
    private contratoService: ContratoService,
    private messageService: MessageService
  ) {}

  ngOnInit() {
    this.carregarContratos();
    this.inicializarFormulario();
  }

  carregarContratos() {
    this.contratoService.listarContratos().subscribe({
      next: (contratos) => {
        this.contratos = contratos;
      },
      error: () => {
        this.erro = 'Erro ao carregar contratos.';
      }
    });
  }

  inicializarFormulario() {
    if (this.ordemServico) {
      this.editando = true;
      this.formData = {
        codigoContrato: this.ordemServico.codigoContrato,
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
    if (!this.formData.codigoContrato?.trim()) {
      this.erro = 'Código do contrato é obrigatório.';
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