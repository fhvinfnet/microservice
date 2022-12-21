package br.edu.infnet.cliente.model.domain;

import br.edu.infnet.cliente.exceptions.CpfInvalidoException;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String email;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, String email) throws CpfInvalidoException {

        if (cpf == null) {
            throw new CpfInvalidoException("Não é possível aceitar CPF nulo");
        }

        if (cpf.isBlank()) {
            throw new CpfInvalidoException("Não é possível aceitar CPF vazio");
        }

        this.cpf = cpf;
        this.email = email;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome + ";" + cpf + ";" + email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
