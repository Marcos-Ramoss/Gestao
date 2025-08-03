import { RecursoResponseDto } from "./recurso-response.dto";

export interface RelatorioAtividadeDto {
  cliente: string;
  ano: number;
  mes: number;
  recurso: RecursoResponseDto;
  nomeProjeto: string;
  horaTotalProjeto: number;
} 