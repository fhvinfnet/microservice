package br.edu.infnet.venda.service;

import br.edu.infnet.venda.clients.ProdutoClient;
import br.edu.infnet.venda.model.ClienteModel;
import br.edu.infnet.venda.model.ProdutoModel;
import br.edu.infnet.venda.model.domain.Venda;
import br.edu.infnet.venda.repository.VendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class VendaService {

    private static Logger log = LoggerFactory.getLogger(VendaService.class);

    @Autowired
    private ProdutoClient produtoClient;

    @Value("${cliente.endpoint.url}")
    private String clienteApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    VendaRepository vendaRepository;

    public Collection<Venda> obterLista() {
        return (Collection<Venda>) vendaRepository.findAll();
    }

    public Venda obterPorId(Integer id) {
        return vendaRepository.findById(id).get();
    }

    @Transactional
    public Venda incluir(Venda venda) {
        ClienteModel clienteModel = getCliente(venda);

        ResponseEntity<List<ProdutoModel>> produtos = getProdutos();

        return vendaRepository.save(venda);
    }

    public void excluir(Integer id) {
        vendaRepository.deleteById(id);
    }

    private ClienteModel getCliente(Venda venda) {
        try {
            log.info("chamada a api de clientes com clienteId:{}", venda.getClienteId());

            return restTemplate.getForObject(clienteApiUrl + venda.getClienteId(), ClienteModel.class);
        } catch (Exception e) {
            log.error("erro tentando obter dados do cliente");
            throw e;
        }
    }

    private ResponseEntity<List<ProdutoModel>> getProdutos() {
        try {
            log.info("chamada a api de produtos para obter a lista dos produtos");

            return produtoClient.getProdutos();
        } catch (Exception e) {
            log.error("erro tentando obter os dados dos produtos");
            throw e;
        }
    }

}
