package com.winy.gerenciador_plano_de_saude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GerenciadorPlanoDeSaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPlanoDeSaudeApplication.class, args);
	}

}
