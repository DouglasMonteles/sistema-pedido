package br.com.douglas.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.douglas.domain.Pedido;
import br.com.douglas.repository.PedidoRepository;
import br.com.douglas.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	private PedidoRepository repository;
	
	public PedidoService(PedidoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
