import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrdemServicoRepository } from '../repository/ordem-servico.repository';
import { OrdemServicoRequestDto } from '../dto/ordem-servico-request.dto';
import { OrdemServicoResponseDto } from '../dto/ordem-servico-response.dto';

@Injectable({ providedIn: 'root' })
export class OrdemServicoService {
  constructor(private ordemServicoRepository: OrdemServicoRepository) {}

  listarOrdensServico(): Observable<OrdemServicoResponseDto[]> {
    return this.ordemServicoRepository.listarOrdensServico();
  }

  buscarOrdemServicoPorId(id: number): Observable<OrdemServicoResponseDto> {
    return this.ordemServicoRepository.buscarOrdemServicoPorId(id);
  }

  criarOrdemServico(data: OrdemServicoRequestDto): Observable<OrdemServicoResponseDto> {
    return this.ordemServicoRepository.criarOrdemServico(data);
  }

  atualizarOrdemServico(id: number, data: OrdemServicoRequestDto): Observable<OrdemServicoResponseDto> {
    return this.ordemServicoRepository.atualizarOrdemServico(id, data);
  }

  deletarOrdemServico(id: number): Observable<void> {
    return this.ordemServicoRepository.deletarOrdemServico(id);
  }
} 