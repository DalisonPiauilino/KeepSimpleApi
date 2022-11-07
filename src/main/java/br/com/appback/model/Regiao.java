package br.com.appback.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Regiao {
    private Geracao geracao;
    private Compra compra;
}
