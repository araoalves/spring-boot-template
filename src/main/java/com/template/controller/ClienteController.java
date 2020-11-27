package com.template.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.template.business.IClienteBO;
import com.template.model.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@CrossOrigin()
@RequestMapping(value = "/clientes")
@Api(value="clientes", description="operações de clientes")
public class ClienteController {
	
	@Autowired
	@Qualifier("teste_bean")
	private String testeBean;
	
	@Autowired
	private IClienteBO clienteBO;
	
	@ApiOperation(value = "Mostrar lista de clientes", authorizations = { @Authorization(value="apiKey") })
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de clientes"),
		    @ApiResponse(code = 401, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@RequestMapping(value = "/listarClientes", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarClientes() {	
		try {
			List<Cliente> clientes = (List<Cliente>) clienteBO.findAll();
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}		
		
    }
	
	@ApiOperation(value = "Cadastrar clientes", authorizations = { @Authorization(value="apiKey") })
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) throws Exception {		
		try {
			return new ResponseEntity<>(clienteBO.save(cliente), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
		
	@ApiOperation(value = "Excluir cliente", authorizations = { @Authorization(value="apiKey") })
	@DeleteMapping("excluirCliente/{id}")	
	public ResponseEntity<HttpStatus> excluirCliente(@PathVariable("id") long id) {
		try {
			clienteBO.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@ApiOperation(value = "Editar cliente", authorizations = { @Authorization(value="apiKey") })
	@PutMapping("editarCliente/{id}")
	public ResponseEntity<Cliente> editarCliente(@PathVariable("id") long id, @RequestBody Cliente cliente) {				
		try {
			Optional<Cliente> clienteAtual = clienteBO.findById(id);

			if (clienteAtual.isPresent()) {
				Cliente _cliente = clienteAtual.get();
				_cliente.setNome(cliente.getNome());
				_cliente.setTelefone(cliente.getTelefone());
				return new ResponseEntity<>(clienteBO.save(_cliente), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}		
	}
	
	@RequestMapping(value = "/listarClientesSqlQuery", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listarClientesSqlQuery() {	
		try {
			List<Cliente> clientes = (List<Cliente>) clienteBO.listarClientesSqlQuery();
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}		
    }
		
	@ApiOperation(value = "Enviar Cliente RebbitMq", authorizations = { @Authorization(value="apiKey") })
	@RequestMapping(value = "/enviarClienteRebbitMq", method = RequestMethod.POST)
	public ResponseEntity<?> enviarClienteRebbitMq(@RequestBody Cliente cliente) throws Exception {		
		try {
			return new ResponseEntity<>(clienteBO.enviarClienteRebbitMq(cliente), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
