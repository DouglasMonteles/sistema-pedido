package br.com.douglas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.douglas.domain.Categoria;
import br.com.douglas.domain.dto.CategoriaDTO;
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
	
	public List<Categoria> findAll() {
		return repository.findAll();
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
		Categoria newObj = this.find(obj.getId());
		
		this.updateData(newObj, obj);
		
		return repository.save(newObj);
	}
	
	public void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao excluir! Não é possivel excluir uma categoria que possui produtos relacionados à ela");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(request);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
}
