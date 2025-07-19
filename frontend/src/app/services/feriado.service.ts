import { Injectable } from '@angular/core';
import { FeriadoRepository } from '../repository/feriado.repository';
import { FeriadoRequestDto } from '../dto/feriado-request.dto';
import { FeriadoResponseDto } from '../dto/feriado-response.dto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FeriadoService {
  constructor(private feriadoRepository: FeriadoRepository) {}

  listarFeriados(): Observable<FeriadoResponseDto[]> {
    return this.feriadoRepository.listarFeriados();
  }

  buscarFeriadoPorId(id: number): Observable<FeriadoResponseDto> {
    return this.feriadoRepository.buscarFeriadoPorId(id);
  }

  criarFeriado(data: FeriadoRequestDto): Observable<FeriadoResponseDto> {
    return this.feriadoRepository.criarFeriado(data);
  }

  atualizarFeriado(id: number, data: FeriadoRequestDto): Observable<FeriadoResponseDto> {
    return this.feriadoRepository.atualizarFeriado(id, data);
  }

  deletarFeriado(id: number): Observable<void> {
    return this.feriadoRepository.deletarFeriado(id);
  }
} 