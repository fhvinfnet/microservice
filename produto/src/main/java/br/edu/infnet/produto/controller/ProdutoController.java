package br.edu.infnet.produto.controller;

import br.edu.infnet.produto.assembler.ProdutoInputDisassembler;
import br.edu.infnet.produto.assembler.ProdutoModelAssembler;
import br.edu.infnet.produto.model.ProdutoInput;
import br.edu.infnet.produto.model.ProdutoModel;
import br.edu.infnet.produto.model.domain.Produto;
import br.edu.infnet.produto.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoModelAssembler assembler;

    @Autowired
    ProdutoInputDisassembler disassembler;

    private static Logger log = LoggerFactory.getLogger(ProdutoController.class);
    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public Collection<ProdutoModel> obterLista() {
        log.info("API de Produto - obterLista");

        return assembler.toCollection(produtoService.obterLista());
    }

    @GetMapping("/{id}")
    public ProdutoModel obterProduto(@PathVariable Integer id) {
        log.info("API de Produto - obterProduto");  

        return assembler.toModel(produtoService.obterPorId(id));
    }

    @PostMapping
    public ProdutoModel incluir(@RequestBody ProdutoInput produtoInput) {
        log.info("API de Produto - incluir");

        Produto produto = disassembler.toDomainObject(produtoInput);

        return assembler.toModel(produtoService.incluir(produto));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        log.info("API de Produto - excluir");

        produtoService.excluir(id);
    }
}
