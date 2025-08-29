package br.com.industria.desafio.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Anotação do Lombok que gera Getters, Setters, toString, equals e hashCode
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;

}