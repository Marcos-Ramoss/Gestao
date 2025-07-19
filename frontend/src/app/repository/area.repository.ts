import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseRepository } from './base.repository';
import { AreaRequestDto } from '../dto/area-request.dto';
import { AreaResponseDto } from '../dto/area-response.dto';

@Injectable({ providedIn: 'root' })
export class AreaRepository extends BaseRepository {
  private readonly URL = this.apiUrl + '/areas';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  listarAreas(): Observable<AreaResponseDto[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<AreaResponseDto[]>(this.URL, { headers });
  }

  buscarAreaPorId(id: number): Observable<AreaResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.get<AreaResponseDto>(`${this.URL}/${id}`, { headers });
  }

  criarArea(data: AreaRequestDto): Observable<AreaResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.post<AreaResponseDto>(this.URL, data, { headers });
  }

  atualizarArea(id: number, data: AreaRequestDto): Observable<AreaResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.put<AreaResponseDto>(`${this.URL}/${id}`, data, { headers });
  }

  deletarArea(id: number): Observable<void> {
    const headers = this.getAuthHeaders();
    return this.http.delete<void>(`${this.URL}/${id}`, { headers });
  }
} 