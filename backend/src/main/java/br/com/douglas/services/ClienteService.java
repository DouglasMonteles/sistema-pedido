package br.com.douglas.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.douglas.domain.Cliente;
import br.com.douglas.repository.ClienteRepository;
import br.com.douglas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id));
	}
	
}
