package br.com.xyinc.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.xyinc.entities.PontoInteresse;

public interface RepositorioPontoInteresse extends MongoRepository<PontoInteresse, String> {

}
