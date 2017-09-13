package br.com.xyinc.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.xyinc.entities.PontoInteresse;
import br.com.xyinc.servico.PontoInteresseServico;

@Controller
@RequestMapping(value = "/pontos-interesse")
public class PontoInteresseResource {

	// TODO terminar anotações classe

	@Autowired
	private PontoInteresseServico servico;

	@GetMapping
	public @ResponseBody Collection<PontoInteresse> requestTodosPontosInteresse() {
		return servico.listaTodosPontosInteresse();
	}

	@GetMapping(value = "/proximos")
	public @ResponseBody Collection<PontoInteresse> requestTodosPontosInteresseProximos(
			@RequestParam("coordenadaX") Integer coordenadaX, @RequestParam("coordenadaY") Integer coordenadaY,
			@RequestParam("distancia") Double distancia) {
		return servico.listaPontosInteresseProximos(coordenadaX, coordenadaY, distancia);
	}

	@PostMapping
	public ResponseEntity<PontoInteresse> savePointOfInteresf(@Validated @RequestBody PontoInteresse pontoInteresse,
			Errors errors) {

		PontoInteresse poi = servico.registrarPontoInteresse(pontoInteresse, errors);
		HttpStatus status = (null == poi || !poi.getErrosEncontrados().isEmpty()) ? HttpStatus.CONFLICT
				: HttpStatus.CREATED;

		return new ResponseEntity<PontoInteresse>(poi, status);
	}
}
