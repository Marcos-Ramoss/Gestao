import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RelatorioAtividadeDto } from '../dto/relatorio-atividade.dto';
import { RelatorioRepository } from '../repository/relatorio.repository';

@Injectable({ providedIn: 'root' })
export class RelatorioService {
  constructor(private relatorioRepository: RelatorioRepository) {}

  uploadRelatorios(ano: number, mes: number, files: File[]): Observable<RelatorioAtividadeDto[]> {
    // Aqui pode ser adicionada lógica de validação ou transformação se necessário
    return this.relatorioRepository.uploadRelatorios(ano, mes, files);
  }

  listarRelatorios(ano: number, mes: number): Observable<RelatorioAtividadeDto[]> {
    // Aqui pode ser adicionada lógica de negócio, cache, etc.
    return this.relatorioRepository.listarRelatorios(ano, mes);
  }
} 