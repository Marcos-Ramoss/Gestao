import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BaseRepository } from './base.repository';
import { ContratoRequestDto } from '../dto/contrato-request.dto';
import { ContratoResponseDto } from '../dto/contrato-response.dto';

@Injectable({ providedIn: 'root' })
export class ContratoRepository extends BaseRepository {
  private readonly URL = this.apiUrl + '/contratos';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  criarContrato(dto: ContratoRequestDto): Observable<ContratoResponseDto> {
    return this.http.post<ContratoResponseDto>(this.URL, dto, { headers: this.getAuthHeaders() });
  }

  listarContratos(): Observable<ContratoResponseDto[]> {
    return this.http.get<ContratoResponseDto[]>(this.URL, { headers: this.getAuthHeaders() });
  }

  buscarPorCodigo(codigoContrato: string): Observable<ContratoResponseDto> {
    return this.http.get<ContratoResponseDto>(`${this.URL}/${codigoContrato}`, { headers: this.getAuthHeaders() });
  }

  atualizarContrato(codigoContrato: string, dto: ContratoRequestDto): Observable<ContratoResponseDto> {
    return this.http.put<ContratoResponseDto>(`${this.URL}/${codigoContrato}`, dto, { headers: this.getAuthHeaders() });
  }

  deletarContrato(codigoContrato: string): Observable<void> {
    return this.http.delete<void>(`${this.URL}/${codigoContrato}`, { headers: this.getAuthHeaders() });
  }
} 