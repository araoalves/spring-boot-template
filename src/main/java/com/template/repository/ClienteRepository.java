package com.template.repository;

import org.springframework.data.repository.CrudRepository;

import com.template.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
}
