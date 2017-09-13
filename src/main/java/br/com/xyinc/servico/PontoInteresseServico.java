package br.com.xyinc.servico;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

		List<String> errosEncontrados = new ArrayList<>();

		if (null == entity) {
			return null;
		}
		if (erros.hasErrors()) {
			erros.getFieldErrors().iterator().forEachRemaining(item -> {
				entity.adicionaErroEncontrado(item.getDefaultMessage());
				errosEncontrados.add(item.getDefaultMessage());

			});
			return entity;
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
			if (this.isPontoInteresseEProximo(coordenadaX, coordenadaY, distancia, poi)) {
				pontosProximos.add(poi);
			}
		});
		return pontosProximos;
	}

	private Boolean isPontoInteresseEProximo(Integer pontoReferenciaX, Integer pontoReferenciaY, Double distancia,
			PontoInteresse poi) {

		Double resultadoLatitudesX = calculaDistanciaLatitudesLongitudes(poi.getCoordenadaX().doubleValue(),
				pontoReferenciaX.doubleValue());
		Double resultadoLongitudeY = calculaDistanciaLatitudesLongitudes(poi.getCoordenadaY().doubleValue(),
				pontoReferenciaY.doubleValue());

		BigDecimal perimetro = new BigDecimal(distancia).setScale(10);

		return new BigDecimal(determinaPontosProximosPlanoCartesiano(resultadoLatitudesX, resultadoLongitudeY))
				.setScale(10, RoundingMode.HALF_EVEN).compareTo(perimetro) <= BigDecimal.ZERO.intValue();

	}

	private Double calculaDistanciaLatitudesLongitudes(Double valor1, Double valor2) {
		return Math.pow((valor1 - valor2), 2);
	}

	private Double determinaPontosProximosPlanoCartesiano(Double latitudes, Double longitudes) {
		return Math.sqrt((latitudes + longitudes));
	}

	// private Boolean calculaDistanciaPontosMaisProximos(Integer
	// pontoReferenciaX, Integer pontoReferenciaY,
	// Double distancia, PontoInteresse poi) {
	//
	// Double resultadoLatitudesX = Math.pow((poi.getCoordenadaX().doubleValue()
	// - pontoReferenciaX.doubleValue()), 2);
	// Double resultadoLongitudeY = Math.pow((poi.getCoordenadaY().doubleValue()
	// - pontoReferenciaY.doubleValue()), 2);
	// Double resultadoDiferenca = Math.sqrt((resultadoLatitudesX +
	// resultadoLongitudeY));
	//
	// BigDecimal perimetro = new BigDecimal(distancia).setScale(10);
	// BigDecimal resultDiferenca = new
	// BigDecimal(resultadoDiferenca).setScale(10, RoundingMode.HALF_EVEN);
	//
	// return resultDiferenca.compareTo(perimetro) <=
	// BigDecimal.ZERO.intValue();
	//
	// }

}
