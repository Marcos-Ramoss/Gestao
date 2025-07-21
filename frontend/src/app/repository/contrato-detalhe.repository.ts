import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseRepository } from './base.repository';
import { ContratoDetalheRequestDto } from '../dto/contrato-detalhe-request.dto';
import { ContratoDetalheResponseDto } from '../dto/contrato-detalhe-response.dto';

@Injectable({ providedIn: 'root' })
export class ContratoDetalheRepository extends BaseRepository {
  constructor(http: HttpClient) {
    super(http);
  }

  listarContratoDetalhes(): Observable<ContratoDetalheResponseDto[]> {
    return this.http.get<ContratoDetalheResponseDto[]>(`${this.apiUrl}/contrato-detalhes`, {
      headers: this.getAuthHeaders()
    });
  }

  buscarContratoDetalhePorId(id: number): Observable<ContratoDetalheResponseDto> {
    return this.http.get<ContratoDetalheResponseDto>(`${this.apiUrl}/contrato-detalhes/${id}`, {
      headers: this.getAuthHeaders()
    });
  }

  criarContratoDetalhe(data: ContratoDetalheRequestDto): Observable<ContratoDetalheResponseDto> {
    return this.http.post<ContratoDetalheResponseDto>(`${this.apiUrl}/contrato-detalhes`, data, {
      headers: this.getAuthHeaders()
    });
  }

  atualizarContratoDetalhe(id: number, data: ContratoDetalheRequestDto): Observable<ContratoDetalheResponseDto> {
    return this.http.put<ContratoDetalheResponseDto>(`${this.apiUrl}/contrato-detalhes/${id}`, data, {
      headers: this.getAuthHeaders()
    });
  }

  deletarContratoDetalhe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/contrato-detalhes/${id}`, {
      headers: this.getAuthHeaders()
    });
  }
} 