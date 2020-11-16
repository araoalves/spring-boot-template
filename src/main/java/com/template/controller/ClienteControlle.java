package com.template.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.template.model.Cliente;
import com.template.repository.ClienteRepository;

@RestController
@CrossOrigin()
@RequestMapping(value = "/clientes")
public class ClienteControlle {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(value = "/listarClientes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarClientes() {
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) throws Exception {		
		try {
			return new ResponseEntity<>(clienteRepository.save(cliente), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@DeleteMapping("excluirCliente/{id}")
	public ResponseEntity<HttpStatus> excluirCliente(@PathVariable("id") long id) {
		try {
			clienteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("editarCliente/{id}")
	public ResponseEntity<Cliente> editarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {
		
		Optional<Cliente> clienteAtual = clienteRepository.findById(id);

		if (clienteAtual.isPresent()) {
			Cliente _cliente = clienteAtual.get();
			_cliente.setNome(cliente.getNome());
			_cliente.setTelefone(cliente.getTelefone());
			return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
