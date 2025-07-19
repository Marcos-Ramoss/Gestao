import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseRepository } from './base.repository';
import { FeriadoRequestDto } from '../dto/feriado-request.dto';
import { FeriadoResponseDto } from '../dto/feriado-response.dto';

@Injectable({ providedIn: 'root' })
export class FeriadoRepository extends BaseRepository {
  private readonly URL = this.apiUrl + '/feriados';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  listarFeriados(): Observable<FeriadoResponseDto[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<FeriadoResponseDto[]>(this.URL, { headers });
  }

  buscarFeriadoPorId(id: number): Observable<FeriadoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.get<FeriadoResponseDto>(`${this.URL}/${id}`, { headers });
  }

  criarFeriado(data: FeriadoRequestDto): Observable<FeriadoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.post<FeriadoResponseDto>(this.URL, data, { headers });
  }

  atualizarFeriado(id: number, data: FeriadoRequestDto): Observable<FeriadoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.put<FeriadoResponseDto>(`${this.URL}/${id}`, data, { headers });
  }

  deletarFeriado(id: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.URL}/${id}`, { headers });
  }
} 