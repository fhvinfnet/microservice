package br.edu.infnet.cliente.service;

import br.edu.infnet.cliente.clients.VendaClient;
import br.edu.infnet.cliente.exceptions.ClienteComVendaException;
import br.edu.infnet.cliente.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.cliente.model.VendaModel;
import br.edu.infnet.cliente.model.domain.Cliente;
import br.edu.infnet.cliente.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private VendaClient vendaClient;

    private static Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    ClienteRepository clienteRepository;

    public Collection<Cliente> obterLista() {
        return (Collection<Cliente>) clienteRepository.findAll();
    }

    public Cliente obterCliente(Integer clienteId) {
        return getOrElseThrow(clienteId);
    }

    public Cliente incluir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluir(Integer id) {
        ResponseEntity<List<VendaModel>> vendas = obterVendas();

        VendaModel venda = new VendaModel();

        venda.setId(id);

        if (vendas.getBody().contains(venda)) {
            String msg = String.format("cliente com id %s nao pode ser excluido pois tem venda registrada para ele", id);

            log.error(msg, id);

            throw new ClienteComVendaException(msg);
        } else {
            clienteRepository.deleteById(id);
        }
    }

    private Cliente getOrElseThrow(Integer clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> {
                    String msg = String.format("cliente com id %s nao encontrado", clienteId);
                    log.error(msg);
                    return new ClienteNaoEncontradoException(msg);
                });
    }

    private ResponseEntity<List<VendaModel>> obterVendas() {
        try {
            log.info("chamada a api de vendas para obter a lista de vendas");

            return vendaClient.getVendas();
        } catch(Exception e) {
            log.error("erro na chamada a api de vendas");
            throw e;
        }
    }
}
