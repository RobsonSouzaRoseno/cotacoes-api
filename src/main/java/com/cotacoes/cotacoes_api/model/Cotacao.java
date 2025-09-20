package com.cotacoes.cotacoes_api.model;

import lombok.Data;

@Data
public class Cotacao {
    private String moeda;
    private String codigo;
    private String   valorEmReais;

    public Cotacao() {
    }

    public Cotacao(String moeda, String codigo, String valorEmReais) {
        this.moeda = moeda;
        this.codigo = codigo;
        this.valorEmReais = valorEmReais;
    }
}
