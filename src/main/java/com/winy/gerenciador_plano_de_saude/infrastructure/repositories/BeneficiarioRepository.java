package com.winy.gerenciador_plano_de_saude.infrastructure.repositories;

import com.winy.gerenciador_plano_de_saude.infrastructure.entities.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
}
