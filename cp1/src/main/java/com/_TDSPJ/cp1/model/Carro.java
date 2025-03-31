package com._TDSPJ.cp1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name="CARRO")
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;
    private @Getter @Setter String modelo;
    private @Getter @Setter String marca;
    private @Getter @Setter Long ano;
    private @Getter @Setter Long potencia;
    private @Getter @Setter Tipo tipo;
    private @Getter @Setter String preco;

    public Carro(Long id, String modelo, String marca, Long ano, Long potencia, Tipo tipo, String preco) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.potencia = potencia;
        this.tipo = tipo;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

