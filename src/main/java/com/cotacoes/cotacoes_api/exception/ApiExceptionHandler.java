package com.cotacoes.cotacoes_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CotacaoNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handCotacaoNaoEncontrada(CotacaoNaoEncontradaException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("erro", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
