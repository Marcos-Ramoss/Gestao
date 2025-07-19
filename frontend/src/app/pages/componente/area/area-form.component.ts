import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AreaService } from '../../../services/area.service';
import { AreaRequestDto } from '../../../dto/area-request.dto';
import { AreaResponseDto } from '../../../dto/area-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-area-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ButtonModule, InputTextModule, NgIf, ToastModule],
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

  constructor(private fb: FormBuilder, private areaService: AreaService, private messageService: MessageService, public router: Router) {
    this.areaForm = this.fb.group({
      nome: ['', [Validators.required, Validators.maxLength(100)]]
    });
  }

  ngOnInit() {
    if (this.area) {
      this.areaForm.patchValue({ nome: this.area.nome });
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['area']) {
      this.sucesso = null;
      this.erro = null;
      if (this.area) {
        this.areaForm.patchValue({ nome: this.area.nome });
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
      const data: AreaRequestDto = { nome: this.areaForm.value.nome };
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