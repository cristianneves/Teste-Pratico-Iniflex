package br.com.industria.desafio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    // Construtor com todos os argumentos, incluindo os da classe pai
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento); // Chama o construtor da classe pai (Pessoa)
        this.salario = salario;
        this.funcao = funcao;
    }
}