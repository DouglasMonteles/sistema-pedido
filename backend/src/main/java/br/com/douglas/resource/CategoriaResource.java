package br.com.douglas.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglas.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria c1 = new Categoria(1, "Cama, mesa e banho");
		Categoria c2 = new Categoria(2, "Informática");
		
		return Arrays.asList(c1, c2);
	}
	
}
