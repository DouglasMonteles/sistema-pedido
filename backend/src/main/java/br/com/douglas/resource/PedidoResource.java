package br.com.douglas.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglas.domain.Pedido;
import br.com.douglas.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	private PedidoService service;
	
	public PedidoResource(PedidoService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
}
