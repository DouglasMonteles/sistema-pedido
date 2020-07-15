package br.com.douglas.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.douglas.domain.Categoria;
import br.com.douglas.repository.CategoriaRepository;
import br.com.douglas.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	private CategoriaRepository repository;
	
	public CategoriaService(CategoriaRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Categoria não encontrada! Id: " + id
				));
	}
	
}
