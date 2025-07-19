import { Component, Input, OnInit, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RecursoService } from '../../../services/recurso.service';
import { RecursoRequestDto } from '../../../dto/recurso-request.dto';
import { RecursoResponseDto } from '../../../dto/recurso-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';
import { ContratoService } from '../../../services/contrato.service';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';

@Component({
  selector: 'app-recurso-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    NgIf,
    ToastModule,
    DropdownModule
  ],
  providers: [MessageService],
  templateUrl: 'recurso-form.component.html'
})
export class RecursoFormComponent implements OnInit, OnChanges {
  @Input() recurso?: RecursoResponseDto; // Se vier, é edição
  @Output() salvo = new EventEmitter<void>();
  recursoForm!: FormGroup;
  carregando = false;
  sucesso: string | null = null;
  erro: string | null = null;
  contratos: ContratoResponseDto[] = [];

  constructor(private fb: FormBuilder, private recursoService: RecursoService, private messageService: MessageService, private router: Router, private contratoService: ContratoService) {
    this.recursoForm = this.fb.group({
      codigoContrato: ['', Validators.required],
      nome: ['', Validators.required],
      fatorAjuste: [null, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit() {
    this.contratoService.listarContratos().subscribe({
      next: (dados) => this.contratos = dados,
      error: () => this.contratos = []
    });
    if (this.recurso) {
      this.recursoForm.patchValue(this.recurso);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['recurso']) {
      this.sucesso = null;
      this.erro = null;
      if (this.recurso) {
        this.recursoForm.patchValue(this.recurso);
      }
    }
  }

  onSubmit() {
    if (this.recursoForm.valid) {
      this.carregando = true;
      this.sucesso = null;
      this.erro = null;
      const data: RecursoRequestDto = this.recursoForm.value;
      if (this.recurso) {
        // Edição
        this.recursoService.atualizarRecurso(this.recurso.id, data).subscribe({
          next: () => {
            this.sucesso = 'Recurso atualizado com sucesso!';
            this.carregando = false;
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Recurso atualizado com sucesso!'});
            this.salvo.emit();
            setTimeout(() => {
              this.router.navigate(['/app/recursos']);
            }, 1500);
          },
          error: () => {
            this.erro = 'Erro ao atualizar recurso.';
            this.carregando = false;
          }
        });
      } else {
        // Cadastro
        this.recursoService.criarRecurso(data).subscribe({
          next: () => {
            this.sucesso = 'Recurso cadastrado com sucesso!';
            this.carregando = false;
            this.recursoForm.reset();
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Recurso cadastrado com sucesso!'});
            this.salvo.emit();
            setTimeout(() => {
              this.router.navigate(['/app/recursos']);
            }, 1500);
          },
          error: () => {
            this.erro = 'Erro ao cadastrar recurso.';
            this.carregando = false;
          }
        });
      }
    } else {
      this.recursoForm.markAllAsTouched();
    }
  }
} 