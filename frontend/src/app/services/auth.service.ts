import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRepository } from '../repository/auth.repository';
import { AuthRequestDto } from '../dto/auth-request.dto';

@Injectable({ providedIn: 'root' })
export class AuthService {
  constructor(private authRepository: AuthRepository) {}

  login(username: string, password: string): Observable<{token: string, nome: string}> {
    return this.authRepository.login(username, password);
  }

  register(data: AuthRequestDto): Observable<any> {
    return this.authRepository.register(data);
  }

  getPerfil() {
    return this.authRepository.getPerfil();
  }

  updatePerfil(data: import('../dto/perfil-request.dto').PerfilRequestDto) {
    return this.authRepository.updatePerfil(data);
  }
} 