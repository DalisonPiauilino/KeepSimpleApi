package br.com.appback.model;

import lombok.Data;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Agente {

    private String codigo;
    private Timestamp data;
    private List<Regiao> regiao;

}