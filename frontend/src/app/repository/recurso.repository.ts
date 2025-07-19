import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseRepository } from './base.repository';
import { RecursoRequestDto } from '../dto/recurso-request.dto';
import { RecursoResponseDto } from '../dto/recurso-response.dto';

@Injectable({ providedIn: 'root' })
export class RecursoRepository extends BaseRepository {
  private readonly URL = this.apiUrl + '/recursos';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  listarRecursos(): Observable<RecursoResponseDto[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<RecursoResponseDto[]>(this.URL, { headers });
  }

  buscarRecursoPorId(id: number): Observable<RecursoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.get<RecursoResponseDto>(`${this.URL}/${id}`, { headers });
  }

  criarRecurso(data: RecursoRequestDto): Observable<RecursoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.post<RecursoResponseDto>(this.URL, data, { headers });
  }

  atualizarRecurso(id: number, data: RecursoRequestDto): Observable<RecursoResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.put<RecursoResponseDto>(`${this.URL}/${id}`, data, { headers });
  }

  deletarRecurso(id: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.URL}/${id}`, { headers });
  }
} 