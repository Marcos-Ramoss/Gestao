package com.service.setebit.gestao.application.service;

import com.service.setebit.gestao.adapter.dto.AreaRequest;
import com.service.setebit.gestao.adapter.dto.AreaResponse;
import com.service.setebit.gestao.domain.AreaDomain;
import com.service.setebit.gestao.domain.repository.AreaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Serviço responsável pela lógica de negócio relacionada às áreas.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AreaService {
    
    private final AreaRepository areaRepository;

    /**
     * Cria uma nova área no sistema.
     * 
     * @param request Dados da área a ser criada
     * @return Área criada
     * @throws IllegalArgumentException se os dados forem inválidos
     */
    public AreaResponse criar(AreaRequest request) {
        log.info("Iniciando criação de área: {}", request.nome());
        
        // Validação adicional de negócio
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            log.error("Tentativa de criar área com nome vazio");
            throw new IllegalArgumentException("Nome da área não pode ser vazio");
        }
        
        // Verifica se já existe uma área com o mesmo nome
        Optional<AreaDomain> areaExistente = areaRepository.listarTodos().stream()
                .filter(area -> area.getNome().equalsIgnoreCase(request.nome().trim()))
                .findFirst();
        
        if (areaExistente.isPresent()) {
            log.warn("Tentativa de criar área com nome duplicado: {}", request.nome());
            throw new IllegalArgumentException("Já existe uma área com o nome: " + request.nome());
        }
        
        AreaDomain domain = AreaDomain.builder()
                .nome(request.nome().trim())
                .build();
        
        AreaDomain salvo = areaRepository.salvar(domain);
        log.info("Área criada com sucesso. ID: {}, Nome: {}", salvo.getId(), salvo.getNome());
        
        return toResponse(salvo);
    }

    /**
     * Lista todas as áreas cadastradas no sistema.
     * 
     * @return Lista de todas as áreas
     */
    public List<AreaResponse> listarTodos() {
        log.info("Buscando todas as áreas cadastradas");
        List<AreaDomain> areas = areaRepository.listarTodos();
        log.info("Encontradas {} áreas", areas.size());
        
        return areas.stream()
                .map(this::toResponse)
                .collect(toList());
    }

    /**
     * Busca uma área específica pelo ID.
     * 
     * @param id ID da área a ser buscada
     * @return Optional contendo a área se encontrada
     */
    public Optional<AreaResponse> buscarPorId(Long id) {
        log.info("Buscando área com ID: {}", id);
        
        if (id == null || id <= 0) {
            log.warn("ID inválido fornecido: {}", id);
            return Optional.empty();
        }
        
        Optional<AreaDomain> area = areaRepository.buscarPorId(id);
        
        if (area.isPresent()) {
            log.info("Área encontrada: ID={}, Nome={}", id, area.get().getNome());
        } else {
            log.warn("Área não encontrada com ID: {}", id);
        }
        
        return area.map(this::toResponse);
    }

    /**
     * Atualiza uma área existente.
     * 
     * @param id ID da área a ser atualizada
     * @param request Novos dados da área
     * @return Área atualizada
     * @throws IllegalArgumentException se a área não existir ou os dados forem inválidos
     */
    public AreaResponse atualizar(Long id, AreaRequest request) {
        log.info("Iniciando atualização da área com ID: {}", id);
        
        if (id == null || id <= 0) {
            log.error("ID inválido fornecido para atualização: {}", id);
            throw new IllegalArgumentException("ID da área é obrigatório e deve ser maior que zero");
        }
        
        // Verifica se a área existe
        Optional<AreaDomain> areaExistente = areaRepository.buscarPorId(id);
        if (areaExistente.isEmpty()) {
            log.error("Tentativa de atualizar área inexistente com ID: {}", id);
            throw new IllegalArgumentException("Área não encontrada com ID: " + id);
        }
        
        // Validação do nome
        if (request.nome() == null || request.nome().trim().isEmpty()) {
            log.error("Tentativa de atualizar área com nome vazio. ID: {}", id);
            throw new IllegalArgumentException("Nome da área não pode ser vazio");
        }
        
        // Verifica se já existe outra área com o mesmo nome (excluindo a atual)
        Optional<AreaDomain> areaComMesmoNome = areaRepository.listarTodos().stream()
                .filter(area -> area.getNome().equalsIgnoreCase(request.nome().trim()) 
                        && !area.getId().equals(id))
                .findFirst();
        
        if (areaComMesmoNome.isPresent()) {
            log.warn("Tentativa de atualizar área com nome duplicado: {}. ID: {}", request.nome(), id);
            throw new IllegalArgumentException("Já existe outra área com o nome: " + request.nome());
        }
        
        AreaDomain domain = AreaDomain.builder()
                .id(id)
                .nome(request.nome().trim())
                .build();
        
        AreaDomain atualizado = areaRepository.salvar(domain);
        log.info("Área atualizada com sucesso. ID: {}, Nome: {}", id, atualizado.getNome());
        
        return toResponse(atualizado);
    }

    /**
     * Remove uma área do sistema.
     * 
     * @param id ID da área a ser removida
     * @throws IllegalArgumentException se a área não existir
     */
    public void deletar(Long id) {
        log.info("Iniciando remoção da área com ID: {}", id);
        
        if (id == null || id <= 0) {
            log.error("ID inválido fornecido para remoção: {}", id);
            throw new IllegalArgumentException("ID da área é obrigatório e deve ser maior que zero");
        }
        
        // Verifica se a área existe
        Optional<AreaDomain> areaExistente = areaRepository.buscarPorId(id);
        if (areaExistente.isEmpty()) {
            log.error("Tentativa de remover área inexistente com ID: {}", id);
            throw new IllegalArgumentException("Área não encontrada com ID: " + id);
        }
        
        // TODO: Adicionar validação se a área está sendo usada em contratos
        // areaRepository.verificarUsoEmContratos(id);
        
        areaRepository.deletarPorId(id);
        log.info("Área removida com sucesso. ID: {}", id);
    }

    /**
     * Converte um domínio de área para DTO de resposta.
     * 
     * @param domain Domínio da área
     * @return DTO de resposta
     */
    private AreaResponse toResponse(AreaDomain domain) {
        return new AreaResponse(
                domain.getId(),
                domain.getNome()
        );
    }
} 