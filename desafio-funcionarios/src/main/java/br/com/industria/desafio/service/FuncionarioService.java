package br.com.industria.desafio.service;

import br.com.industria.desafio.model.Funcionario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final List<Funcionario> funcionarios = new ArrayList<>();

    public void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    public void removerFuncionario(String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public List<Funcionario> getFuncionarios() {
        return new ArrayList<>(this.funcionarios);
    }

    public void aplicarAumentoSalario(BigDecimal percentual) {
        BigDecimal fatorAumento = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));
        for (Funcionario f : this.funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(fatorAumento);
            f.setSalario(novoSalario);
        }
    }

    public Map<String, List<Funcionario>> agruparPorFuncao() {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public List<Funcionario> getAniversariantesDoMes(Integer... meses) {
        Set<Integer> mesesDesejados = Set.of(meses); // Usar um Set para performance
        return funcionarios.stream()
                .filter(f -> mesesDesejados.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    public Optional<Funcionario> getFuncionarioMaisVelho() {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    public List<Funcionario> getFuncionariosOrdenadosPorNome() {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalSalarios() {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}