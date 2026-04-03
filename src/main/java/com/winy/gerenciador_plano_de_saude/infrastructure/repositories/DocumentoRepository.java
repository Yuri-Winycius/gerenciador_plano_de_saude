package com.winy.gerenciador_plano_de_saude.infrastructure.repositories;

import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
