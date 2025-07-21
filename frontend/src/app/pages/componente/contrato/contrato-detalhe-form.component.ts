import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ContratoDetalheService } from '../../../services/contrato-detalhe.service';
import { ContratoDetalheRequestDto } from '../../../dto/contrato-detalhe-request.dto';
import { ContratoDetalheResponseDto } from '../../../dto/contrato-detalhe-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-contrato-detalhe-form',
  standalone: true,
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, ToastModule, NgIf],
  providers: [MessageService],
  templateUrl: './contrato-detalhe-form.component.html'
})
export class ContratoDetalheFormComponent implements OnInit, OnChanges {
  @Input() detalhe: ContratoDetalheResponseDto | null = null;
  @Input() codigoContrato: string = '';
  @Output() fechar = new EventEmitter<boolean>();
  form: FormGroup;
  loading = false;
  erro: string | null = null;

  constructor(
    private fb: FormBuilder,
    private service: ContratoDetalheService,
    private messageService: MessageService
  ) {
    this.form = this.fb.group({
      preposto: ['', Validators.required],
      fiscal: ['', Validators.required],
      gestor: ['', Validators.required],
      objeto: ['', Validators.required],
      processoSei: ['', Validators.required]
    });
  }

  ngOnInit() {
    if (this.detalhe) {
      this.form.patchValue(this.detalhe);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['detalhe'] && this.detalhe) {
      this.form.patchValue(this.detalhe);
    } else if (changes['detalhe'] && !this.detalhe) {
      this.form.reset();
    }
  }

  salvar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.loading = true;
    this.erro = null;
    const dto: ContratoDetalheRequestDto = {
      codigoContrato: this.codigoContrato,
      ...this.form.value
    };
    const operacao = this.detalhe
      ? this.service.atualizarContratoDetalhe(this.detalhe.id, dto)
      : this.service.criarContratoDetalhe(dto);
    operacao.subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Sucesso',
          detail: this.detalhe ? 'Detalhe atualizado com sucesso!' : 'Detalhe criado com sucesso!'
        });
        this.loading = false;
        setTimeout(() => this.fechar.emit(true), 1000);
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro',
          detail: this.detalhe ? 'Erro ao atualizar detalhe.' : 'Erro ao criar detalhe.'
        });
        this.loading = false;
        this.erro = this.detalhe ? 'Erro ao atualizar detalhe.' : 'Erro ao criar detalhe.';
      }
    });
  }

  cancelar() {
    this.fechar.emit(false);
  }
} 