package com.cotacoes.cotacoes_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CotacoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotacoesApiApplication.class, args);
	}

	public static class CotacaoNaoEncontradaException extends RuntimeException {
		public CotacaoNaoEncontradaException(String codigo) {
			super("Cotação não encontrada para o  codigo: " +codigo);
		}
	}
}
