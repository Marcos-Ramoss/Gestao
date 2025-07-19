import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { BaseRepository } from './base.repository';
import { AuthRequestDto } from '../dto/auth-request.dto';
import { PerfilResponseDto } from '../dto/perfil-response.dto';
import { PerfilRequestDto } from '../dto/perfil-request.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthRepository extends BaseRepository {

  private readonly URL_LOGIN = this.apiUrl + '/auth/login';
  private readonly URL_REGISTER = this.apiUrl + '/auth/register';
  private readonly URL_BUSCAR_PERFIL = this.apiUrl + '/auth/perfil';
  private readonly URL_ATUALIZAR_PERFIL = this.apiUrl + '/auth/perfil';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  login(username: string, password: string): Observable<{ token: string, nome: string }> {
    const headers = this.getAuthHeaders();
    return this.http.post<{ token: string, nome: string }>(this.URL_LOGIN, { username, password }, { headers });
  }

  register(data: AuthRequestDto): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post<any>(this.URL_REGISTER, data, { headers });
  }

  getPerfil(): Observable<PerfilResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.get<PerfilResponseDto>( this.URL_BUSCAR_PERFIL , { headers });
  }

  updatePerfil(data: PerfilRequestDto): Observable<PerfilResponseDto> {
    const headers = this.getAuthHeaders();
    return this.http.put<PerfilResponseDto>(this.URL_ATUALIZAR_PERFIL, data, { headers });
  }
}