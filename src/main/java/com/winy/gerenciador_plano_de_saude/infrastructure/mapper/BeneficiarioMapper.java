package com.winy.gerenciador_plano_de_saude.infrastructure.mapper;

import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioRequest;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioResponse;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.DocumentoDTO;
import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Beneficiario;
import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Documento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeneficiarioMapper {

    public Beneficiario toEntity(BeneficiarioRequest req) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(req.nome());
        beneficiario.setTelefone(req.telefone());
        beneficiario.setDataNascimento(req.dataNascimento());

        List<Documento> documentos = req.documentos().stream().map(d -> {
            Documento documento = new Documento();
            documento.setTipoDocumento(d.tipoDocumento());
            documento.setDescricao(d.descricao());
            documento.setBeneficiario(beneficiario);

            return documento;
        }).toList();

        beneficiario.setDocumentos(documentos);

        return beneficiario;
    }

    public BeneficiarioResponse toResponse(Beneficiario beneficiario) {
        List<DocumentoDTO> documentoDTO = beneficiario.getDocumentos().stream().map(d ->
                new DocumentoDTO(d.getTipoDocumento(), d.getDescricao())).toList();

        return new BeneficiarioResponse(beneficiario.getId(), beneficiario.getNome(),
                beneficiario.getTelefone(), beneficiario.getDataNascimento(), documentoDTO);
    }

}
