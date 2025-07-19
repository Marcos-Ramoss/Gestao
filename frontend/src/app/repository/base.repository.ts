import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export abstract class BaseRepository {
  protected readonly apiUrl = 'http://localhost:8080';

  constructor(protected http: HttpClient) {}

  protected getAuthHeaders(extra?: HttpHeaders): HttpHeaders {
    const token = localStorage.getItem('token');
    if (!token) return extra || new HttpHeaders();
    if (!extra) {
      return new HttpHeaders({ Authorization: `Bearer ${token}` });
    } else {
      return extra.set('Authorization', `Bearer ${token}`);
    }
  }
} 