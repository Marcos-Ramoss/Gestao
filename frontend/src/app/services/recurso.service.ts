import { Injectable } from '@angular/core';
import { RecursoRepository } from '../repository/recurso.repository';
import { RecursoRequestDto } from '../dto/recurso-request.dto';
import { RecursoResponseDto } from '../dto/recurso-response.dto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class RecursoService {
  constructor(private recursoRepository: RecursoRepository) {}

  listarRecursos(): Observable<RecursoResponseDto[]> {
    return this.recursoRepository.listarRecursos();
  }

  buscarRecursoPorId(id: number): Observable<RecursoResponseDto> {
    return this.recursoRepository.buscarRecursoPorId(id);
  }

  criarRecurso(data: RecursoRequestDto): Observable<RecursoResponseDto> {
    return this.recursoRepository.criarRecurso(data);
  }

  atualizarRecurso(id: number, data: RecursoRequestDto): Observable<RecursoResponseDto> {
    return this.recursoRepository.atualizarRecurso(id, data);
  }

  deletarRecurso(id: number): Observable<void> {
    return this.recursoRepository.deletarRecurso(id);
  }
} 