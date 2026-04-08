package com.winy.gerenciador_plano_de_saude.controller;

import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioRequest;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.BeneficiarioResponse;
import com.winy.gerenciador_plano_de_saude.infrastructure.dto.DocumentoDTO;
import com.winy.gerenciador_plano_de_saude.service.BeneficiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    public BeneficiarioController(BeneficiarioService beneficiarioService) {
        this.beneficiarioService = beneficiarioService;
    }

    @PostMapping
    public ResponseEntity<BeneficiarioResponse> criarBeneficiario(@RequestBody BeneficiarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioService.salvarBeneficiario(request));
    }

    @GetMapping
    public List<BeneficiarioResponse> listarTodosBeneficiarios() {
        return beneficiarioService.listarTodosBeneficiarios();
    }

    @GetMapping("/{id}/documentos")
    public List<DocumentoDTO> listarDocumentos(@PathVariable Long id) {
        return beneficiarioService.listarDocumentos(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerBeneficiario(@PathVariable Long id) {
        beneficiarioService.removerBeneficiario(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResponse> atualizarBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioRequest request) {
        return ResponseEntity.ok(beneficiarioService.atualizarBeneficiario(id, request));
    }

}
