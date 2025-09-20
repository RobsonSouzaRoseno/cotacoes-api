package com.cotacoes.cotacoes_api.exception;

public class CotacaoNaoEncontradaException extends Throwable {
    public CotacaoNaoEncontradaException(String codigo) {
        super("Cotação não encontrada para o codigo" + codigo.toUpperCase());
    }
}
