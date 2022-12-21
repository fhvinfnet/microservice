package br.edu.infnet.cliente.clients;

import br.edu.infnet.cliente.model.VendaModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("venda")
public interface VendaClient {

    @GetMapping("/vendas")
    ResponseEntity<List<VendaModel>> getVendas();
}
