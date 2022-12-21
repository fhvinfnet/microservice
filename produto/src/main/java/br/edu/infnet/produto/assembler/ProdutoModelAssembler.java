package br.edu.infnet.produto.assembler;

import br.edu.infnet.produto.model.ProdutoModel;
import br.edu.infnet.produto.model.domain.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProdutoModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        ProdutoModel produtoModel = new ProdutoModel();

        modelMapper.map(produto, produtoModel);

        return produtoModel;
    }

    public List<ProdutoModel> toCollection(Iterable<Produto> entities) {

        return StreamSupport.stream(entities.spliterator(), false)
                .map(e -> toModel(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
