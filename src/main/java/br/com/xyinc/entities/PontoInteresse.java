package br.com.xyinc.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe responsável por representar o modelo a ser persistido.
 * 
 * @author alex
 *
 */
@Document(collection = "pontointeresse")
public class PontoInteresse {

	@Id
	private String id;
	@NotEmpty(message = "Campo obrigatório! (Nome Ponto inresse não pode ser nulo.)")
	private String nome;
	@NotNull(message = "Campo obrigatório! (Coordenada X não pode ser vazia)")
	@Min(value = 0, message = "Valor da Coordenada X deve ser positivo.")
	private Integer coordenadaX;
	@NotNull(message = "Campo obrigatório! (Coordenada Y não pode ser vazia)")
	@Min(value = 0, message = "Valor da Coordenada Y deve positivo")
	private Integer coordenadaY;

	public PontoInteresse() {
		super();
	}

	public PontoInteresse(String id, String nome, Integer coordenadaX, Integer coordenadaY) {
		super();
		this.id = id;
		this.nome = nome;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCoordenadaX() {
		return coordenadaX;
	}

	public void setCoordenadaX(Integer coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	public Integer getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(Integer coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	@Override
	public String toString() {
		return "PontoInteresse [id=" + id + ", nome=" + nome + ", coordenadaX=" + coordenadaX + ", coordenadaY="
				+ coordenadaY + "]";
	}

}
