package br.edu.infnet.produto.service;

import br.edu.infnet.produto.exceptions.ProdutoComVendaException;
import br.edu.infnet.produto.model.VendaModel;
import br.edu.infnet.produto.model.domain.Produto;
import br.edu.infnet.produto.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class ProdutoService {

    @Value("${venda.endpoint.url}")
    private String vendaApiUrl;

    private Logger log = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ProdutoRepository produtoRepository;

    public Collection<Produto> obterLista() {
        return (Collection<Produto>) produtoRepository.findAll();
    }

    public Produto obterPorId(Integer id) {
        return produtoRepository.findById(id).get();
    }

    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void excluir(Integer id) {
        List<VendaModel> vendas = getVendas();

        VendaModel venda = new VendaModel();

        venda.setId(id);

        if (vendas.contains(venda)) {
            String msg = String.format("produto com id %s nao pode ser excluido pois tem venda registrada para ele", id);

            log.error(msg, id);

            throw new ProdutoComVendaException(msg);
        } else {
            produtoRepository.deleteById(id);
        }
    }

    private List<VendaModel> getVendas() {
        try {
            log.info("chamada a api de vendas para obter lista de vendas");

            ResponseEntity<VendaModel[]> response = restTemplate.getForEntity(vendaApiUrl, VendaModel[].class);

            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            log.error("erro tentando obter dados de vendas");
            throw e;
        }
    }

}
