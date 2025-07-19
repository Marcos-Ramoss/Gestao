import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { FileUploadModule } from 'primeng/fileupload';
import { NgIf, NgFor } from '@angular/common';
import { RelatorioService } from '../../../services/relatorio.service';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';
import { RelatorioAtividadeDto } from '../../../dto/relatorio-atividade.dto';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-upload-form',
  templateUrl: './upload-form.component.html',
  standalone: true,
  providers: [MessageService],
  imports: [ReactiveFormsModule, ButtonModule, InputTextModule, FileUploadModule, NgIf, NgFor, ToastModule, TableModule]
})
export class UploadFormComponent {
  @Output() upload = new EventEmitter<{ano: number, mes: number, files: File[]}>();
  uploadForm: FormGroup;
  selectedFiles: File[] = [];
  isUploading = false;
  uploadSuccess = false;
  uploadError: string | null = null;
  relatorios: RelatorioAtividadeDto[] = [];

  constructor(private fb: FormBuilder, private relatorioService: RelatorioService, private messageService: MessageService) {
    this.uploadForm = this.fb.group({
      ano: ['', Validators.required],
      mes: ['', Validators.required],
      files: [null, Validators.required]
    });
  }

  onFileSelect(event: any) {
    const files: File[] = Array.from(event.files);
    this.selectedFiles = [...this.selectedFiles, ...files];
    this.uploadForm.patchValue({ files: this.selectedFiles });
    this.uploadForm.get('files')?.markAsTouched();
  }

  removeFile(index: number) {
    this.selectedFiles.splice(index, 1);
    this.uploadForm.patchValue({ files: this.selectedFiles });
    this.uploadForm.get('files')?.markAsTouched();
  }

  onSubmit() {
    if (this.uploadForm.valid && this.selectedFiles.length > 0) {
      const { ano, mes } = this.uploadForm.value;
      this.isUploading = true;
      this.uploadSuccess = false;
      this.uploadError = null;
      this.relatorios = [];
      this.relatorioService.uploadRelatorios(ano, mes, this.selectedFiles).subscribe({
        next: (dados) => {
          this.isUploading = false;
          this.uploadSuccess = true;
          this.selectedFiles = [];
          this.uploadForm.reset();
          this.messageService.add({severity:'success', summary:'Sucesso', detail:'Upload realizado com sucesso!'});
          this.relatorios = dados; // Exibe apenas os enviados agora
        },
        error: (err) => {
          this.isUploading = false;
          this.uploadError = 'Erro ao enviar arquivos.';
        }
      });
    } else {
      this.uploadForm.markAllAsTouched();
    }
  }

  exportarExcel() {
    if (this.relatorios.length > 0) {
      // Exportar apenas os dados do upload atual
      const dados = this.relatorios.map(rel => ({
        Cliente: rel.cliente,
        Ano: rel.ano,
        Mes: rel.mes,
        Colaborador: rel.colaborador,
        Projeto: rel.nomeProjeto,
        Horas: rel.horaTotalProjeto
      }));
      
      // Criar e baixar o arquivo Excel
      this.downloadExcel(dados, `relatorios_${this.relatorios[0].ano}_${this.relatorios[0].mes}.xlsx`);
    }
  }

  private downloadExcel(data: any[], filename: string) {
    // Implementação simples de exportação para Excel
    const worksheet = XLSX.utils.json_to_sheet(data);
    const workbook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(workbook, worksheet, 'Relatórios');
    XLSX.writeFile(workbook, filename);
  }
} 