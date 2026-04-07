package com.winy.gerenciador_plano_de_saude.service;

import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioRequest;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioResponse;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.DocumentoDTO;
import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Beneficiario;
import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Documento;
import com.winy.gerenciador_plano_de_saude.infrastructure.mapper.BeneficiarioMapper;
import com.winy.gerenciador_plano_de_saude.infrastructure.repositories.BeneficiarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final BeneficiarioMapper beneficiarioMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, BeneficiarioMapper beneficiarioMapper) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.beneficiarioMapper = beneficiarioMapper;
    }

    public BeneficiarioResponse salvarBeneficiario(BeneficiarioRequest request) {
        Beneficiario beneficiario = beneficiarioMapper.toEntity(request);

        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario));
    }

    public List<BeneficiarioResponse> listarTodosBeneficiarios() {
        return beneficiarioRepository.findAll().stream().map(beneficiarioMapper::toResponse).toList();
    }

    public List<DocumentoDTO> listarDocumentos(Long id) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Beneficiario não encontrado."));

        return beneficiario.getDocumentos().stream().map(d ->
                new DocumentoDTO(d.getTipoDocumento(), d.getDescricao())).toList();
    }

    public void removerBeneficiario(Long id) {
        if (!beneficiarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Id do beneficiario não encontrado.");
        }

        beneficiarioRepository.deleteById(id);
    }

    public BeneficiarioResponse atualizarBeneficiario(Long id, BeneficiarioRequest request) {
        Beneficiario beneficiario = beneficiarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Beneficiario não encontrado."));

        beneficiario.setNome(request.nome());
        beneficiario.setDataNascimento(request.dataNascimento());
        beneficiario.getDocumentos().clear();

        for (DocumentoDTO documentoDTO : request.documentos()) {
            Documento documento = new Documento();

            documento.setTipoDocumento(documentoDTO.tipoDocumento());
            documento.setDescricao(documentoDTO.descricao());
            documento.setBeneficiario(beneficiario);
            beneficiario.getDocumentos().add(documento);
        }

        return beneficiarioMapper.toResponse(beneficiarioRepository.save(beneficiario));
    }

}
