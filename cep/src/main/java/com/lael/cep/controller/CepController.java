package com.lael.cep.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lael.cep.model.Log;
import com.lael.cep.model.RespostaDTO;
import com.lael.cep.repositories.LogRepository;
import com.lael.cep.services.CepService;

@RestController
@RequestMapping("/")
public class CepController {

    @Autowired
    private CepService cepService;

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/consulta-cep/{cep}")
    public ResponseEntity<Map<String, String>> consultaCep(@PathVariable String cep) {
       RespostaDTO DTO = cepService.consultaCep(cep);
       
        Log log = new Log(cep, DTO.getUf(), LocalDateTime.now());
        logRepository.save(log);

        Map<String, String> response = new HashMap<>();
        response.put("cep", DTO.getCep());
        response.put("rua", DTO.getLogradouro());
        response.put("bairro", DTO.getBairro());
        response.put("cidade", DTO.getLocalidade());
        response.put("uf", DTO.getUf());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/lista-ceps")
    public ResponseEntity<List<Map<String, String>>> listaCepsPorUf(@RequestParam String uf) {
        List<Log> logs = logRepository.findTop20ByUfOrderByDtHrConsultaDesc(uf);

        List<Map<String, String>> response = logs.stream().map(log -> {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("cep", log.getCep());
            map.put("dt_hr_consulta", log.getDtHrConsulta() .toLocalDate().toString());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}

