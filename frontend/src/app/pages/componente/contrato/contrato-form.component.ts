import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ContratoService } from '../../../services/contrato.service';
import { ContratoRequestDto } from '../../../dto/contrato-request.dto';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contrato-form',
  templateUrl: './contrato-form.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, ToastModule, NgIf, CommonModule],
  providers: [MessageService]
})
export class ContratoFormComponent implements OnInit {
  @Input() contrato: ContratoResponseDto | null = null;
  @Output() fechar = new EventEmitter<boolean>();
  form: FormGroup;
  loading = false;
  erro: string | null = null;

  constructor(
    private fb: FormBuilder, 
    private contratoService: ContratoService, 
    private messageService: MessageService
  ) {
    this.form = this.fb.group({
      codigoContrato: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.atualizarForm();
  }

  ngOnChanges() {
    this.atualizarForm();
  }

  private atualizarForm() {
    if (this.contrato) {
      this.form.patchValue({ codigoContrato: this.contrato.codigoContrato });
      this.form.get('codigoContrato')?.enable();
    } else {
      this.form.reset();
      this.form.get('codigoContrato')?.enable();
    }
    this.erro = null;
    this.loading = false;
  }

  salvar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.loading = true;
    this.erro = null;
    const dto: ContratoRequestDto = {
      codigoContrato: this.form.getRawValue().codigoContrato
    };
    if (this.contrato) {
      // Atualiza usando o valor antigo como chave, mas envia o novo valor
      this.contratoService.atualizarContrato(this.contrato.codigoContrato, dto).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Sucesso',
            detail: 'Contrato atualizado com sucesso!'
          });
          this.loading = false;
          setTimeout(() => {
            this.fechar.emit(true);
          }, 1000);
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Erro ao atualizar contrato'
          });
          this.erro = 'Erro ao atualizar contrato';
          this.loading = false;
        }
      });
    } else {
      this.contratoService.criarContrato(dto).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Sucesso',
            detail: 'Contrato criado com sucesso!'
          });
          this.loading = false;
          setTimeout(() => {
            this.fechar.emit(true);
          }, 1000);
        },
        error: () => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Erro ao criar contrato'
          });
          this.erro = 'Erro ao criar contrato';
          this.loading = false;
        }
      });
    }
  }

  cancelar() {
    this.fechar.emit(false);
  }
} 