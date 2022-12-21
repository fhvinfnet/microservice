package br.edu.infnet.venda.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class VendaModel {

    private Integer id;
    private String descricao;
    private LocalDateTime data;
    private boolean web;
    private Integer clienteId;
    private Set<VendaProdutoModel> produtos = new HashSet<>();

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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Set<VendaProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<VendaProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
