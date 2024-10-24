package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cliente;
import com.example.demo.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Iterable<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> findById(Integer id){
		return clienteRepository.findById(id);
	}

	public Cliente save(Cliente cliente) {
			clienteRepository.save(cliente);
			return cliente;
	}

	public void delete(Integer id) {
		clienteRepository.deleteById(id);
	}

}

