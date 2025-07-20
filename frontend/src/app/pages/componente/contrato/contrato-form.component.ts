import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ContratoService } from '../../../services/contrato.service';
import { ContratoRequestDto } from '../../../dto/contrato-request.dto';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { NgIf } from '@angular/common';
import { AreaService } from '../../../services/area.service';
import { AreaResponseDto } from '../../../dto/area-response.dto';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contrato-form',
  templateUrl: './contrato-form.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, DropdownModule, ToastModule, NgIf, CommonModule],
  providers: [MessageService]
})
export class ContratoFormComponent implements OnChanges, OnInit {
  @Input() contrato: ContratoResponseDto | null = null;
  @Output() fechar = new EventEmitter<boolean>();
  form: FormGroup;
  loading = false;
  erro: string | null = null;
  areas: AreaResponseDto[] = [];

  constructor(
    private fb: FormBuilder, 
    private contratoService: ContratoService, 
    private areaService: AreaService,
    private messageService: MessageService
  ) {
    this.form = this.fb.group({
      codigoContrato: ['', Validators.required],
      idArea: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.carregarAreas();
  }

  carregarAreas() {
    this.areaService.listarAreas().subscribe({
      next: (dados) => {
        this.areas = dados;
        console.log('Áreas carregadas:', dados);
      },
      error: (erro) => {
        console.error('Erro ao carregar áreas:', erro);
        this.areas = [];
      }
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['contrato']) {
      if (this.contrato) {
        this.form.patchValue(this.contrato);
        this.form.get('codigoContrato')?.disable();
      } else {
        this.form.reset();
        this.form.get('codigoContrato')?.enable();
      }
      this.erro = null;
      this.loading = false;
    }
  }

  salvar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    
    this.loading = true;
    this.erro = null;
    const dto: ContratoRequestDto = {
      codigoContrato: this.form.getRawValue().codigoContrato,
      idArea: +this.form.get('idArea')?.value
    };
    if (this.contrato) {
      this.contratoService.atualizarContrato(this.contrato.codigoContrato, dto).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Sucesso',
            detail: 'Contrato atualizado com sucesso!'
          });
          this.loading = false;
          // Pequeno delay para mostrar a mensagem antes de fechar o modal
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
          // Pequeno delay para mostrar a mensagem antes de fechar o modal
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