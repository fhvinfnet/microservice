package br.edu.infnet.venda.clients;

import br.edu.infnet.venda.model.ProdutoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("produto")
public interface ProdutoClient {

    @GetMapping("/produtos")
    ResponseEntity<List<ProdutoModel>> getProdutos();
}
