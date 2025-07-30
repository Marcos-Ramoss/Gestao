import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AreaService } from '../../../services/area.service';
import { ContratoService } from '../../../services/contrato.service';
import { AreaRequestDto } from '../../../dto/area-request.dto';
import { AreaResponseDto } from '../../../dto/area-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-area-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ButtonModule, InputTextModule, DropdownModule, NgIf, ToastModule],
  providers: [MessageService],
  templateUrl: './area-form.component.html',
})
export class AreaFormComponent implements OnInit, OnChanges {
  @Input() area?: AreaResponseDto;
  @Output() salvo = new EventEmitter<void>();
  areaForm!: FormGroup;
  carregando = false;
  sucesso: string | null = null;
  erro: string | null = null;
  contratos: any[] = [];

  constructor(
    private fb: FormBuilder,
    private areaService: AreaService,
    private contratoService: ContratoService,
    private messageService: MessageService,
    public router: Router
  ) {
    this.areaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(255)]],
      codigoContrato: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    // Buscar contratos para o dropdown
    this.contratoService.listarContratos().subscribe({
      next: (contratos) => {
        this.contratos = contratos;
      },
      error: () => {
        this.erro = 'Erro ao carregar contratos.';
      }
    });
    if (this.area) {
      this.areaForm.patchValue({ nome: this.area.nome, codigoContrato: this.area.codigoContrato });
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['area']) {
      this.sucesso = null;
      this.erro = null;
      if (this.area) {
        this.areaForm.patchValue({ nome: this.area.nome, codigoContrato: this.area.codigoContrato });
      } else {
        this.areaForm.reset();
      }
    }
  }

  onSubmit() {
    if (this.areaForm.valid) {
      this.carregando = true;
      this.sucesso = null;
      this.erro = null;
      const data: AreaRequestDto = {
        nome: this.areaForm.value.nome,
        codigoContrato: this.areaForm.value.codigoContrato
      };
      if (this.area) {
        this.areaService.atualizarArea(this.area.id, data).subscribe({
          next: () => {
            this.sucesso = 'Área atualizada com sucesso!';
            this.carregando = false;
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Área atualizada com sucesso!'});
            setTimeout(() => this.router.navigate(['/app/areas']), 1000);
            this.salvo.emit();
          },
          error: () => {
            this.erro = 'Erro ao atualizar área.';
            this.carregando = false;
          }
        });
      } else {
        this.areaService.criarArea(data).subscribe({
          next: () => {
            this.sucesso = 'Área cadastrada com sucesso!';
            this.carregando = false;
            this.areaForm.reset();
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Área cadastrada com sucesso!'});
            setTimeout(() => this.router.navigate(['/app/areas']), 1000);
            this.salvo.emit();
          },
          error: () => {
            this.erro = 'Erro ao cadastrar área.';
            this.carregando = false;
          }
        });
      }
    } else {
      this.areaForm.markAllAsTouched();
    }
  }
} 