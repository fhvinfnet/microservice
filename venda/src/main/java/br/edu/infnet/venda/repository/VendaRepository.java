package br.edu.infnet.venda.repository;

import br.edu.infnet.venda.model.domain.Venda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Integer> {

}
