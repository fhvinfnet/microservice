package br.edu.infnet.venda.controller;

import br.edu.infnet.venda.assembler.VendaInputDisassembler;
import br.edu.infnet.venda.assembler.VendaModelAssembler;
import br.edu.infnet.venda.model.VendaInput;
import br.edu.infnet.venda.model.VendaModel;
import br.edu.infnet.venda.model.domain.Venda;
import br.edu.infnet.venda.service.VendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    VendaModelAssembler assembler;

    @Autowired
    VendaInputDisassembler disassembler;

    private static Logger log = LoggerFactory.getLogger(VendaController.class);

    @Autowired
    VendaService vendaService;

    @GetMapping
    public Collection<VendaModel> obterLista() {
        log.info("API de Venda - obterLista");

        return assembler.toCollection(vendaService.obterLista());
    }

    @GetMapping("/{id}")
    public VendaModel obterCliente(@PathVariable Integer id) {
        log.info("API de Venda - obterCliente");

        return assembler.toModel(vendaService.obterPorId(id));
    }

    @PostMapping
    public VendaModel incluir(@RequestBody VendaInput vendaInput) {
        log.info("API de Venda - incluir");

        Venda venda = disassembler.toDomainObject(vendaInput);

        return assembler.toModel(vendaService.incluir(venda));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        log.info("API de Venda - excluir");

        vendaService.excluir(id);
    }
}
