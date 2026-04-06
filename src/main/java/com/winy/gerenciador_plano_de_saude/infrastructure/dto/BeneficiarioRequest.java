package com.winy.gerenciador_plano_de_saude.infrastructure.dto;

import java.time.LocalDate;
import java.util.List;

public record BeneficiarioRequest(String nome, String telefone, LocalDate dataNascimento, List<DocumentoDTO> documentos) {
}
