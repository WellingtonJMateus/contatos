package com.well.contatos.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco extends RepresentationModel<Endereco> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID idEndereco;
    private String rua;
    private String bairro;
    private String numero;
    private String cep;
    private String estado;

    @OneToOne(mappedBy = "endereco")
    private Contato contato;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public UUID getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(UUID idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}