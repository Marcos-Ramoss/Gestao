import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RelatorioAtividadeDto } from '../../../dto/relatorio-atividade.dto';
import { RelatorioService } from '../../../services/relatorio.service';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { NgIf } from '@angular/common';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';

@Component({
  selector: 'app-relatorio-atividade',
  templateUrl: './relatorio-atividade.component.html',
  styleUrl: './relatorio-atividade.component.scss',  
  standalone: true,
  providers: [MessageService],
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, NgIf, ToastModule, TableModule, DropdownModule]
})
export class RelatorioAtividadeComponent {
  
  uploadForm: FormGroup;
  isUploading = false;
  uploadSuccess = false;
  uploadError: string | null = null;
  relatorios: RelatorioAtividadeDto[] = [];
  meses = [
    { id: 1, nome: 'Janeiro' },
    { id: 2, nome: 'Fevereiro' },
    { id: 3, nome: 'Março' },
    { id: 4, nome: 'Abril' },
    { id: 5, nome: 'Maio' },
    { id: 6, nome: 'Junho' },
    { id: 7, nome: 'Julho' },
    { id: 8, nome: 'Agosto' },
    { id: 9, nome: 'Setembro' },
    { id: 10, nome: 'Outubro' },
    { id: 11, nome: 'Novembro' },
    { id: 12, nome: 'Dezembro' }
  ];

  constructor(
    private fb: FormBuilder, 
    private relatorioService: RelatorioService, 
    private messageService: MessageService) {
    this.uploadForm = this.fb.group({
      ano: ['', Validators.required],
      mes: ['', Validators.required],
      files: [null, Validators.required]
    });

    const hoje = new Date();
    this.uploadForm.patchValue({
      ano: hoje.getFullYear(),
      mes: hoje.getMonth() + 1
    });
  }

  pesquisarRelatorios() {
    const ano = this.uploadForm.get('ano')?.value;
    const mes = this.uploadForm.get('mes')?.value;
    if (ano && mes) {
      this.relatorioService.listarRelatorios(ano, mes).subscribe({
        next: (res) => this.relatorios = Array.isArray(res) ? res : [res],
        error: (err) => {/* tratar erro */}
      });
    }
  }


  exportarExcel() {
    // TODO: Implementar exportação para Excel
    this.messageService.add({severity:'info', summary:'Exportação', detail:'Funcionalidade de exportação ainda não implementada.'});
  }
}
