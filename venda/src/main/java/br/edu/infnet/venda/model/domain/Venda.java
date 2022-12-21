package br.edu.infnet.venda.model.domain;

import br.edu.infnet.venda.exceptions.ClienteNuloException;
import br.edu.infnet.venda.exceptions.VendaSemProdutoException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private LocalDateTime data;
    private boolean web;
    private Integer clienteId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venda_id")
    private Set<VendaProduto> produtos = new HashSet<>();

    public Venda() {
        this.data = LocalDateTime.now();
        this.web = true;
    }

    public Venda(Integer clienteId, Set<VendaProduto> produtos) throws ClienteNuloException, VendaSemProdutoException {
        this();

        if (clienteId == null) {
            throw new ClienteNuloException("cliente deve ser informado");
        }

        this.clienteId = clienteId;
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return descricao + ";" + data + ";" + web + ";" + clienteId + ";" + produtos.size();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer cliente) {
        this.clienteId = cliente;
    }

    public Set<VendaProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<VendaProduto> vendaProdutos) {
        this.produtos = vendaProdutos;
    }
}
