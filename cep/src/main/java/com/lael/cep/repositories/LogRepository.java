package com.lael.cep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lael.cep.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	List<Log> findTop20ByUfOrderByDtHrConsultaDesc(String uf);

}
