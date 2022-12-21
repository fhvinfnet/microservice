package br.edu.infnet.produto.model;

public class ProdutoInput {

    private String nome;
    private float valor;
    private int codigo;

    @Override
    public String toString() {
        return nome + ";" + valor + ";" + codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
