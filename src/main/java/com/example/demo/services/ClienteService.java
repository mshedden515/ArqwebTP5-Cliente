package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.FacturacionPorClienteDTO;
import com.example.demo.dto.ReporteComprasClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repositories.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private final ClienteRepository clienteRepository;
	
	@Autowired 
	private RestTemplate restTemplate;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public Iterable<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public ResponseEntity<Cliente> findById(Integer id){
		try{
			Cliente c = clienteRepository.findById(id).get();
			return new ResponseEntity<Cliente>(c,HttpStatus.OK);
			
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		
	}

	public Cliente save(Cliente cliente) {
			clienteRepository.save(cliente);
			return cliente;
	}

	public void delete(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	public ResponseEntity<List<ReporteComprasClienteDTO>> reporteClientes(){
		
			Map<Integer,Cliente> mapaClientes =clienteRepository.findAll().stream().collect(Collectors.toMap(Cliente::getId, Function.identity()));
			//List<FacturacionPorClienteDTO> lista = (List<FacturacionPorClienteDTO>) this.restTemplate.getForEntity("http://localhost:8081/ventas/clientes", ArrayList.class);
			
			ParameterizedTypeReference<List<FacturacionPorClienteDTO>> myBean =
					     new ParameterizedTypeReference<List<FacturacionPorClienteDTO>>() {};
			List<FacturacionPorClienteDTO> response = this.restTemplate.exchange("http://localhost:8083/ventas/clientes",HttpMethod.GET, null,myBean).getBody();
			ArrayList<ReporteComprasClienteDTO> reporte = new ArrayList<ReporteComprasClienteDTO>();
			
			for (FacturacionPorClienteDTO factura : response) {
				String nombre = mapaClientes.get(factura.getCliente()).getNombre();
				double total = factura.getTotal();
				reporte.add(new ReporteComprasClienteDTO(nombre,total));
			}
			
			return ResponseEntity.ok(reporte);
	}

}

