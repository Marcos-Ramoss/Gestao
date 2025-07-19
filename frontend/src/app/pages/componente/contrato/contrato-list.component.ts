import { Component, OnInit } from '@angular/core';
import { ContratoService } from '../../../services/contrato.service';
import { ContratoResponseDto } from '../../../dto/contrato-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { NgIf, NgFor } from '@angular/common';
import { ContratoFormComponent } from './contrato-form.component';

@Component({
  selector: 'app-contrato-list',
  templateUrl: './contrato-list.component.html',
  standalone: true,
  imports: [TableModule, ButtonModule, DialogModule, ContratoFormComponent]
})
export class ContratoListComponent implements OnInit {
  contratos: ContratoResponseDto[] = [];
  displayForm = false;
  contratoSelecionado: ContratoResponseDto | null = null;

  constructor(private contratoService: ContratoService) {}

  ngOnInit() {
    this.carregarContratos();
  }

  carregarContratos() {
    this.contratoService.listarContratos().subscribe({
      next: (dados) => this.contratos = dados,
      error: () => this.contratos = []
    });
  }

  novoContrato() {
    this.contratoSelecionado = null;
    this.displayForm = true;
  }

  editarContrato(contrato: ContratoResponseDto) {
    this.contratoSelecionado = contrato;
    this.displayForm = true;
  }

  fecharForm(atualizar: boolean) {
    this.displayForm = false;
    this.contratoSelecionado = null;
    if (atualizar) this.carregarContratos();
  }

  deletarContrato(contrato: ContratoResponseDto) {
    if (confirm('Deseja realmente excluir este contrato?')) {
      this.contratoService.deletarContrato(contrato.codigoContrato).subscribe(() => this.carregarContratos());
    }
  }
} 