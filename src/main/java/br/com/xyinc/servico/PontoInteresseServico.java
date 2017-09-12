package br.com.xyinc.servico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import br.com.xyinc.entities.PontoInteresse;
import br.com.xyinc.repositorio.RepositorioPontoInteresse;

@Service
public class PontoInteresseServico {

	@Autowired
	private RepositorioPontoInteresse repositorio;

	/**
	 * Serviço responsável por fazer o registro de um ponto de interesse
	 * visitado.
	 * 
	 * @param entity
	 * @return
	 */
	public PontoInteresse registrarPontoInteresse(PontoInteresse entity, Errors erros) {

		if (null == entity) {
			return null;
		}
		if (erros.hasErrors()) {
			return null;
		}
		return repositorio.save(entity);
	}

	/**
	 * Serviço responsável por retornar uma lista com todos os pontos de
	 * interesse.
	 * 
	 * @return
	 */
	public Collection<PontoInteresse> listaTodosPontosInteresse() {
		return repositorio.findAll();
	}

	/**
	 * Serviço responsável por retornar todos os pontos de interesse próximos de
	 * uma determinada localização.
	 * 
	 * @param coordenadaX
	 * @param coordenadaY
	 * @param distancia
	 * @return
	 */
	public Collection<PontoInteresse> listaPontosInteresseProximos(Integer coordenadaX, Integer coordenadaY,
			Double distancia) {
		List<PontoInteresse> pontosProximos = new ArrayList<>();

		this.listaTodosPontosInteresse().iterator().forEachRemaining(poi -> {
			if (this.calculaDistanciaPontosMaisProximos(coordenadaX, coordenadaY, distancia, poi)) {
				pontosProximos.add(poi);
			}
		});
		return pontosProximos;
	}

	private Boolean calculaDistanciaPontosMaisProximos(Integer coordenadaX, Integer coordenadaY, Double distancia,
			PontoInteresse poi) {
		// TODO terminar a lógica
		return false;
	}

}
