package com.lael.cep.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "log")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String cep;
    
    private String uf;
    
	@Column( name = "dt_hr_consulta",
			 columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
			)
	private LocalDateTime dtHrConsulta; 
    
    public void registrarConsulta() {
        this.dtHrConsulta = LocalDateTime.now();
    }
    
    
    public Log(String cep, String uf, LocalDateTime dtHrConsulta) {
        this.cep = cep;
        this.uf = uf;
        this.dtHrConsulta = dtHrConsulta;
    }    
    
    
    
    
    
    
    
    
}

