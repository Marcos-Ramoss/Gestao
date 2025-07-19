import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AreaService } from '../../../services/area.service';
import { AreaResponseDto } from '../../../dto/area-response.dto';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { NgIf } from '@angular/common';
import { Router } from '@angular/router';
import { DialogModule } from 'primeng/dialog';
import { AreaFormComponent } from './area-form.component';

@Component({
  selector: 'app-area-list',
  standalone: true,
  imports: [CommonModule, TableModule, ButtonModule, NgIf, DialogModule, AreaFormComponent],
  templateUrl: './area-list.component.html',
})
export class AreaListComponent implements OnInit {
  areas: AreaResponseDto[] = [];
  carregando: boolean = true;
  erro: string | null = null;
  areaSelecionada: AreaResponseDto | null = null;
  mostrarModalEdicao = false;
  mostrarConfirmacaoExclusao = false;
  idParaExcluir: number | null = null;

  constructor(private areaService: AreaService, private router: Router) {}

  ngOnInit() {
    this.carregarAreas();
  }

  carregarAreas() {
    this.carregando = true;
    this.areaService.listarAreas().subscribe({
      next: (dados) => {
        this.areas = dados;
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Erro ao carregar áreas.';
        this.carregando = false;
      }
    });
  }

  editarArea(id: number) {
    const area = this.areas.find(a => a.id === id);
    if (area) {
      this.areaSelecionada = { ...area };
      this.mostrarModalEdicao = true;
    }
  }

  fecharModalEdicao(atualizarLista: boolean = false) {
    this.mostrarModalEdicao = false;
    this.areaSelecionada = null;
    if (atualizarLista) {
      this.carregarAreas();
    }
  }

  deletarArea(id: number) {
    this.idParaExcluir = id;
    this.mostrarConfirmacaoExclusao = true;
  }

  confirmarExclusao() {
    if (this.idParaExcluir !== null) {
      this.areaService.deletarArea(this.idParaExcluir).subscribe({
        next: () => {
          this.mostrarConfirmacaoExclusao = false;
          this.idParaExcluir = null;
          this.carregarAreas();
        },
        error: () => {
          this.mostrarConfirmacaoExclusao = false;
          this.idParaExcluir = null;
          this.erro = 'Erro ao excluir área.';
        }
      });
    }
  }

  cancelarExclusao() {
    this.mostrarConfirmacaoExclusao = false;
    this.idParaExcluir = null;
  }

  novaArea() {
    this.router.navigate(['/app/areas/novo']);
  }
} 