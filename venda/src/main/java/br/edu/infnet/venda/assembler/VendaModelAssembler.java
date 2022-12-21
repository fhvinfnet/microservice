package br.edu.infnet.venda.assembler;

import br.edu.infnet.venda.model.domain.Venda;
import br.edu.infnet.venda.model.VendaModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class VendaModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public VendaModel toModel(Venda venda) {
        VendaModel vendaModel = new VendaModel();

        modelMapper.map(venda, vendaModel);

        return vendaModel;
    }

    public List<VendaModel> toCollection(Iterable<Venda> entities) {

        return StreamSupport.stream(entities.spliterator(), false)
                .map(e -> toModel(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
