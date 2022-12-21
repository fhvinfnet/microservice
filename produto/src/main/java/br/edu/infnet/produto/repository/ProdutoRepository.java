package br.edu.infnet.produto.repository;

import br.edu.infnet.produto.model.domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
