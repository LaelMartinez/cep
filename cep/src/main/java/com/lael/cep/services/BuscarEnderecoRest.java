package com.lael.cep.services;

import com.lael.cep.model.Log;

public interface BuscarEnderecoRest {
	public Log buscarPeloCep(String cep) throws Exception;
}
