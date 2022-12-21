package br.edu.infnet.venda.model;

import java.util.HashSet;
import java.util.Set;

public class VendaInput {

    private String descricao;
    private boolean web;
    private Integer clienteId;
    private Set<VendaProdutoModel> produtos = new HashSet<>();

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
