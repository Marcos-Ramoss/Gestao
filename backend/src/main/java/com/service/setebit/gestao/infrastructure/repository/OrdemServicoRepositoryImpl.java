package com.service.setebit.gestao.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import com.service.setebit.gestao.domain.OrdemServicoDomain;
import com.service.setebit.gestao.domain.repository.OrdemServicoRepository;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;
import com.service.setebit.gestao.infrastructure.entity.OrdemServicoEntity;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OrdemServicoRepositoryImpl implements OrdemServicoRepository {

    private final OrdemServicoEntityRepository jpaRepository;
    private final ContratoEntityRepository contratoRepository;

    @Override
    public OrdemServicoDomain salvar(OrdemServicoDomain ordemServico) {
        ContratoEntity contrato = contratoRepository.findById(ordemServico.getCodigoContrato())
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        OrdemServicoEntity entity = new OrdemServicoEntity();
        entity.setId(ordemServico.getId());
        entity.setContrato(contrato);
        entity.setNumeroOs(ordemServico.getNumeroOs());
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<OrdemServicoDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<OrdemServicoDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private OrdemServicoDomain toDomain(OrdemServicoEntity entity) {
        return OrdemServicoDomain.builder()
                .id(entity.getId())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigo() : null)
                .numeroOs(entity.getNumeroOs())
                .build();
    }
} 