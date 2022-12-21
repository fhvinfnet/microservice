package br.edu.infnet.produto.assembler;

import br.edu.infnet.produto.model.ProdutoInput;
import br.edu.infnet.produto.model.domain.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Produto toDomainObject(ProdutoInput ClienteInput) {
        return modelMapper.map(ClienteInput, Produto.class);
    }

    public void copyToDomainObject(ProdutoInput produtoInput, Produto produto) {
        modelMapper.map(produtoInput, produto);
    }
}
