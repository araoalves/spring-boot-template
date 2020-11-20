package com.template.business;

import java.util.List;
import java.util.Optional;

import com.template.model.Cliente;

public interface IClienteBO {

	List<Cliente> findAll() throws Exception;

	Cliente save(Cliente cliente) throws Exception;

	void deleteById(long id) throws Exception;

	Optional<Cliente> findById(long id) throws Exception;

	List<Cliente> listarClientesSqlQuery() throws Exception;

	Cliente enviarClienteRebbitMq(Cliente cliente);

}
