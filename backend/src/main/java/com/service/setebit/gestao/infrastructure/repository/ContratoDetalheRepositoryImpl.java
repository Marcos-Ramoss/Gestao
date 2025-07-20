package com.service.setebit.gestao.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.service.setebit.gestao.domain.ContratoDetalheDomain;
import com.service.setebit.gestao.domain.repository.ContratoDetalheRepository;
import com.service.setebit.gestao.infrastructure.entity.ContratoDetalheEntity;
import com.service.setebit.gestao.infrastructure.entity.ContratoEntity;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ContratoDetalheRepositoryImpl implements ContratoDetalheRepository {

    private final ContratoDetalheEntityRepository jpaRepository;
    private final ContratoEntityRepository contratoRepository;

    @Override
    public ContratoDetalheDomain salvar(ContratoDetalheDomain detalhe) {
        ContratoEntity contrato = contratoRepository.findById(detalhe.getCodigoContrato())
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado"));
        ContratoDetalheEntity entity = new ContratoDetalheEntity();
        entity.setId(detalhe.getId());
        entity.setContrato(contrato);
        entity.setPreposto(detalhe.getPreposto());
        entity.setFiscal(detalhe.getFiscal());
        entity.setGestor(detalhe.getGestor());
        entity.setObjeto(detalhe.getObjeto());
        entity.setProcessoSei(detalhe.getProcessoSei());
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    @Override
    public Optional<ContratoDetalheDomain> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ContratoDetalheDomain> listarTodos() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(Long id) {
        jpaRepository.deleteById(id);
    }

    private ContratoDetalheDomain toDomain(ContratoDetalheEntity entity) {
        return ContratoDetalheDomain.builder()
                .id(entity.getId())
                .codigoContrato(entity.getContrato() != null ? entity.getContrato().getCodigo() : null)
                .preposto(entity.getPreposto())
                .fiscal(entity.getFiscal())
                .gestor(entity.getGestor())
                .objeto(entity.getObjeto())
                .processoSei(entity.getProcessoSei())
                .build();
    }
} 