package br.com.appback.model;


import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class Compra {

    private List<String> valor;
}