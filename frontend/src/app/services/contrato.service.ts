import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContratoRepository } from '../repository/contrato.repository';
import { ContratoRequestDto } from '../dto/contrato-request.dto';
import { ContratoResponseDto } from '../dto/contrato-response.dto';

@Injectable({ providedIn: 'root' })
export class ContratoService {
  constructor(private contratoRepository: ContratoRepository) {}

  criarContrato(dto: ContratoRequestDto): Observable<ContratoResponseDto> {
    return this.contratoRepository.criarContrato(dto);
  }

  listarContratos(): Observable<ContratoResponseDto[]> {
    return this.contratoRepository.listarContratos();
  }

  buscarPorCodigo(codigoContrato: string): Observable<ContratoResponseDto> {
    return this.contratoRepository.buscarPorCodigo(codigoContrato);
  }

  atualizarContrato(codigoContrato: string, dto: ContratoRequestDto): Observable<ContratoResponseDto> {
    return this.contratoRepository.atualizarContrato(codigoContrato, dto);
  }

  deletarContrato(codigoContrato: string): Observable<void> {
    return this.contratoRepository.deletarContrato(codigoContrato);
  }
} 