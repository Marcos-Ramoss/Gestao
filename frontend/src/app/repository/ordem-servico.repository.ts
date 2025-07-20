import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseRepository } from './base.repository';
import { OrdemServicoRequestDto } from '../dto/ordem-servico-request.dto';
import { OrdemServicoResponseDto } from '../dto/ordem-servico-response.dto';

@Injectable({ providedIn: 'root' })
export class OrdemServicoRepository extends BaseRepository {
  constructor(http: HttpClient) {
    super(http);
  }

  listarOrdensServico(): Observable<OrdemServicoResponseDto[]> {
    return this.http.get<OrdemServicoResponseDto[]>(`${this.apiUrl}/ordens-servico`, {
      headers: this.getAuthHeaders()
    });
  }

  buscarOrdemServicoPorId(id: number): Observable<OrdemServicoResponseDto> {
    return this.http.get<OrdemServicoResponseDto>(`${this.apiUrl}/ordens-servico/${id}`, {
      headers: this.getAuthHeaders()
    });
  }

  criarOrdemServico(data: OrdemServicoRequestDto): Observable<OrdemServicoResponseDto> {
    return this.http.post<OrdemServicoResponseDto>(`${this.apiUrl}/ordens-servico`, data, {
      headers: this.getAuthHeaders()
    });
  }

  atualizarOrdemServico(id: number, data: OrdemServicoRequestDto): Observable<OrdemServicoResponseDto> {
    return this.http.put<OrdemServicoResponseDto>(`${this.apiUrl}/ordens-servico/${id}`, data, {
      headers: this.getAuthHeaders()
    });
  }

  deletarOrdemServico(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/ordens-servico/${id}`, {
      headers: this.getAuthHeaders()
    });
  }
} 