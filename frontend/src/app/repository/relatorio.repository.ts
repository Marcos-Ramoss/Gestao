import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { RelatorioAtividadeDto } from '../dto/relatorio-atividade.dto';
import { BaseRepository } from './base.repository';

@Injectable({
  providedIn: 'root'
})
export class RelatorioRepository extends BaseRepository {

  private readonly URL_UPLOAD_RELATORIOS = this.apiUrl + '/relatorios/upload';
  private readonly URL_LISTAR_RELATORIOS = this.apiUrl + '/relatorios';

  constructor(protected override http: HttpClient) {
    super(http);
  }

  uploadRelatorios(ano: number, mes: number, files: File[]): Observable<RelatorioAtividadeDto[]> {
    const formData = new FormData();
    files.forEach(file => formData.append('files', file));
    const headers = this.getAuthHeaders();
    return this.http.post<RelatorioAtividadeDto[]>(`${this.URL_UPLOAD_RELATORIOS}/${ano}/${mes}`, formData, { headers });
  }

  listarRelatorios(ano: number, mes: number): Observable<RelatorioAtividadeDto[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<RelatorioAtividadeDto[]>(`${this.URL_LISTAR_RELATORIOS}/${ano}/${mes}`, { headers });
  }
}