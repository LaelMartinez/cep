package com.lael.cep.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}

