package com.template.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.template.model.Cliente;
import com.template.repository.IClienteRepository;

@Component
public class ClienteBO implements IClienteBO {
	
	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public List<Cliente> findAll() throws Exception {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente save(Cliente cliente) throws Exception {
		return clienteRepository.save(cliente);
	}

	@Override
	public void deleteById(long id) throws Exception {
		clienteRepository.deleteById(id);
	}

	@Override
	public Optional<Cliente> findById(long id) throws Exception {		
		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> listarClientesSqlQuery() throws Exception {
		return clienteRepository.listarClientesSqlQuery();
	}

}
