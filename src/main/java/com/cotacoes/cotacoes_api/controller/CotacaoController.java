package com.cotacoes.cotacoes_api.controller;
import com.cotacoes.cotacoes_api.exception.CotacaoNaoEncontradaException;
import com.cotacoes.cotacoes_api.service.CotacaoService;
import com.cotacoes.cotacoes_api.model.Cotacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public List<Cotacao> listarTodas() {
        return  cotacaoService.buscarCotacoes();
    }

    @GetMapping("/{codigo}")
    public Cotacao buscarPorCodigo(@PathVariable String codigo) throws CotacaoNaoEncontradaException {
        return cotacaoService.buscarCotacaoUnica(codigo);
    }
}
