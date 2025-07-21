import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContratoDetalheRepository } from '../repository/contrato-detalhe.repository';
import { ContratoDetalheRequestDto } from '../dto/contrato-detalhe-request.dto';
import { ContratoDetalheResponseDto } from '../dto/contrato-detalhe-response.dto';

@Injectable({ providedIn: 'root' })
export class ContratoDetalheService {
  constructor(private contratoDetalheRepository: ContratoDetalheRepository) {}

  listarContratoDetalhes(): Observable<ContratoDetalheResponseDto[]> {
    return this.contratoDetalheRepository.listarContratoDetalhes();
  }

  buscarContratoDetalhePorId(id: number): Observable<ContratoDetalheResponseDto> {
    return this.contratoDetalheRepository.buscarContratoDetalhePorId(id);
  }

  criarContratoDetalhe(data: ContratoDetalheRequestDto): Observable<ContratoDetalheResponseDto> {
    return this.contratoDetalheRepository.criarContratoDetalhe(data);
  }

  atualizarContratoDetalhe(id: number, data: ContratoDetalheRequestDto): Observable<ContratoDetalheResponseDto> {
    return this.contratoDetalheRepository.atualizarContratoDetalhe(id, data);
  }

  deletarContratoDetalhe(id: number): Observable<void> {
    return this.contratoDetalheRepository.deletarContratoDetalhe(id);
  }
} 