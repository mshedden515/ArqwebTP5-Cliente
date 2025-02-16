package com.example.demo.controller;

import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ReporteComprasClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.services.ClienteService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public Iterable<Cliente> getAll(){
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping("/")
	public Cliente save(@RequestBody Cliente cliente) {
		return service.save(cliente);
	}

	@DeleteMapping("/{id}")
	public void DeleteById(@PathVariable Integer id){
		service.delete(id);
	}
	
	@GetMapping("/facturacion")
	public ResponseEntity<List<ReporteComprasClienteDTO>> reporteClientes(){
		return service.reporteClientes();
	}

}
