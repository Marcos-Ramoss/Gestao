import { Injectable } from '@angular/core';
import { AreaRepository } from '../repository/area.repository';
import { AreaRequestDto } from '../dto/area-request.dto';
import { AreaResponseDto } from '../dto/area-response.dto';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AreaService {
  constructor(private areaRepository: AreaRepository) {}

  listarAreas(): Observable<AreaResponseDto[]> {
    return this.areaRepository.listarAreas();
  }

  buscarAreaPorId(id: number): Observable<AreaResponseDto> {
    return this.areaRepository.buscarAreaPorId(id);
  }

  criarArea(data: AreaRequestDto): Observable<AreaResponseDto> {
    return this.areaRepository.criarArea(data);
  }

  atualizarArea(id: number, data: AreaRequestDto): Observable<AreaResponseDto> {
    return this.areaRepository.atualizarArea(id, data);
  }

  deletarArea(id: number): Observable<void> {
    return this.areaRepository.deletarArea(id);
  }
} 