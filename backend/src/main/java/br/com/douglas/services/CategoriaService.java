package br.com.douglas.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.douglas.domain.Categoria;
import br.com.douglas.repository.CategoriaRepository;
import br.com.douglas.services.exceptions.DataIntegrityException;
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		
		return repository.save(obj);
		
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao excluir! Não é possivel excluir uma categoria que possui produtos relacionados à ela");
		}
	}
	
}
