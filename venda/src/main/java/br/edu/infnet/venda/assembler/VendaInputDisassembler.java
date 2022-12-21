package br.edu.infnet.venda.assembler;

import br.edu.infnet.venda.model.domain.Venda;
import br.edu.infnet.venda.model.VendaInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Venda toDomainObject(VendaInput vendaInput) {
        Venda venda = modelMapper.map(vendaInput, Venda.class);

        venda.setId(null);

        return venda;
    }

    public void copyToDomainObject(VendaInput vendaInput, Venda venda) {
        modelMapper.map(vendaInput, venda);
    }
}
