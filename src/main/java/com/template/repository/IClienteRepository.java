package com.template.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.template.model.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Long>{

	@Query(value = "SELECT * FROM cliente ORDER BY id DESC", nativeQuery = true)
	List<Cliente> listarClientesSqlQuery() throws Exception;
	
}
