import { Component, Input, Output, EventEmitter, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FeriadoService } from '../../../services/feriado.service';
import { FeriadoRequestDto } from '../../../dto/feriado-request.dto';
import { FeriadoResponseDto } from '../../../dto/feriado-response.dto';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import { InputMaskModule } from 'primeng/inputmask';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-feriado-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ButtonModule, InputTextModule, CalendarModule, InputMaskModule, NgIf, ToastModule],
  providers: [MessageService],
  templateUrl: './feriado-form.component.html'
})
export class FeriadoFormComponent implements OnInit, OnChanges {
  @Input() feriado?: FeriadoResponseDto;
  @Output() salvo = new EventEmitter<void>();
  feriadoForm!: FormGroup;
  carregando = false;
  sucesso: string | null = null;
  erro: string | null = null;

  constructor(private fb: FormBuilder, private feriadoService: FeriadoService, private messageService: MessageService, private router: Router) {
    this.feriadoForm = this.fb.group({
      data: [null, [Validators.required]]
    });
  }

  ngOnInit() {
    if (this.feriado) {
      this.feriadoForm.patchValue({ data: this.yyyyMMddParaDate(this.feriado.data) });
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['feriado']) {
      this.sucesso = null;
      this.erro = null;
      if (this.feriado) {
        this.feriadoForm.patchValue({ data: this.yyyyMMddParaDate(this.feriado.data) });
      }
    }
  }



  onInputMask(event: any) {
    // Atualiza o valor do formulário ao digitar
    this.feriadoForm.patchValue({ data: event.target.value });
  }

  onSubmit() {
    if (this.feriadoForm.valid) {
      this.carregando = true;
      this.sucesso = null;
      this.erro = null;
      const dataDate: Date = this.feriadoForm.value.data;
      const dataFormatada = this.dateParaYyyyMmDd(dataDate);
      const data: FeriadoRequestDto = { data: dataFormatada };
      if (this.feriado) {
        this.feriadoService.atualizarFeriado(this.feriado.id, data).subscribe({
          next: () => {
            this.sucesso = 'Feriado atualizado com sucesso!';
            this.carregando = false;
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Feriado atualizado com sucesso!'});
            setTimeout(() => this.router.navigate(['/app/feriados']), 1000);
            this.salvo.emit();
          },
          error: () => {
            this.erro = 'Erro ao atualizar feriado.';
            this.carregando = false;
          }
        });
      } else {
        this.feriadoService.criarFeriado(data).subscribe({
          next: () => {
            this.sucesso = 'Feriado cadastrado com sucesso!';
            this.carregando = false;
            this.feriadoForm.reset();
            this.messageService.add({severity:'success', summary:'Sucesso', detail:'Feriado cadastrado com sucesso!'});
            setTimeout(() => this.router.navigate(['/app/feriados']), 1000);
            this.salvo.emit();
          },
          error: () => {
            this.erro = 'Erro ao cadastrar feriado.';
            this.carregando = false;
          }
        });
      }
    } else {
      this.feriadoForm.markAllAsTouched();
    }
  }

  // YYYY-MM-DD -> Date
  yyyyMMddParaDate(data: string): Date {
    const [ano, mes, dia] = data.split('-');
    return new Date(Number(ano), Number(mes) - 1, Number(dia));
  }

  // Date -> YYYY-MM-DD
  dateParaYyyyMmDd(date: Date): string {
    const ano = date.getFullYear();
    const mes = String(date.getMonth() + 1).padStart(2, '0');
    const dia = String(date.getDate()).padStart(2, '0');
    return `${ano}-${mes}-${dia}`;
  }
} 