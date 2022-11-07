package br.com.appback.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class Geracao {

    private List<String> valor;
}
