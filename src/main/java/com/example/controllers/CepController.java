package com.example.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.models.CepModel;

public class CepController {
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

    public CepModel consultarCEP(String cep) {
        String url = String.format(VIA_CEP_URL, cep);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CepModel> response = restTemplate.getForEntity(url, CepModel.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            System.out.println("Erro na requisição: " + response.getStatusCodeValue());
            return null;
        }
    }
}
