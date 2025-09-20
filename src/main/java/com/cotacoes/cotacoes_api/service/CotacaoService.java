package com.cotacoes.cotacoes_api.service;

import com.cotacoes.cotacoes_api.exception.CotacaoNaoEncontradaException;
import com.cotacoes.cotacoes_api.model.Cotacao;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;


import java.util.ArrayList;
import java.util.List;

@Service
public class CotacaoService {

    private final WebClient webClient = WebClient.create();

    public List<Cotacao> buscarCotacoes() {
        List<Cotacao> cotacoes = new ArrayList<>();

        cotacoes.add(new Cotacao("Dólar", "USD", buscar("USD")));
        cotacoes.add(new Cotacao("Euro", "EUR", buscar("EUR")));
        cotacoes.add(new Cotacao("Libra", "GBP", buscar("GBP")));
        cotacoes.add(new Cotacao("Bitcoin", "BTC", buscarBitcoin()));

        return cotacoes;
    }

    private double buscar(String codigoMoeda) {
        try {
            String url = "https://economia.awesomeapi.com.br/json/last/" + codigoMoeda + "-BRL";
            String resposta = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
            JSONObject json = new JSONObject(resposta);

            String chave = codigoMoeda + "BRL"; // Ex: USDBRL
            return json.getJSONObject(chave).getDouble("bid");

        } catch (Exception e) {
            System.out.println("Erro ao buscar cotação de " + codigoMoeda + ": " + e.getMessage());
            return 0.0;
        }
    }



    private double buscarBitcoin() {
        try {
            String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=brl";
            String resposta = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JSONObject json = new JSONObject(resposta);
            return json.getJSONObject("bitcoin").getDouble("brl");
        } catch (Exception e) {
            System.out.println("Erro ao buscar cotação do Bitcoin: " + e.getMessage());
            return 0.0;
        }
    }

    public Cotacao buscarCotacaoUnica(String codigo) throws CotacaoNaoEncontradaException {
        switch (codigo.toUpperCase()) {
            case "USD":
                return new Cotacao("Dolar", "USD", buscar("USD"));
            case "EUR":
                return new Cotacao("Euro", "EUR", buscar("EUR"));
            case "GBP":
                return new Cotacao("Libra", "GBP", buscar("GBP"));
            case "BTC":
                return new Cotacao("Bitcoin", "BTC", buscarBitcoin());
            default:
                throw new CotacaoNaoEncontradaException(codigo);

        }
    }


}
