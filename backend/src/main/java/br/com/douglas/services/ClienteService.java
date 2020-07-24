package br.com.douglas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.douglas.domain.Cliente;
import br.com.douglas.domain.dto.ClienteDTO;
import br.com.douglas.repository.ClienteRepository;
import br.com.douglas.services.exceptions.DataIntegrityException;
import br.com.douglas.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	private ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = this.find(obj.getId());
		
		this.updateData(obj, newObj);
		
		return repository.save(newObj);
	}
	
	public void updateData(Cliente obj, Cliente newObj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		this.find(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir pois existem entidades relacionadas a esse cliente");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(request);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
}
