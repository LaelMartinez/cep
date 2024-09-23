package com.lael.cep.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.lael.cep.model.RespostaDTO;
	
@Service
public class CepService {

	   private final WebClient webClient;

	    public CepService(WebClient.Builder builder) {
	        this.webClient = builder.baseUrl("https://viacep.com.br/ws/").build();
	    }

	    public RespostaDTO consultaCep(String cep) {
	        return this.webClient
	                .get()
	                .uri("{cep}/json/", cep)
	                .retrieve()
	                .bodyToMono(RespostaDTO.class)
	                .block();  
	    }
	}