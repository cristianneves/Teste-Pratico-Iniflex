package br.com.industria.desafio;

import br.com.industria.desafio.model.Funcionario;
import br.com.industria.desafio.service.FuncionarioService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioFuncionariosApplication implements CommandLineRunner {

	@Autowired
	private FuncionarioService funcionarioService;

	// Formatação de data para o padrão brasileiro
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// Formatação de número para o padrão brasileiro
	private final DecimalFormat decimalFormat;

	// Bloco de inicialização para configurar o DecimalFormat com o Locale correto
	public DesafioFuncionariosApplication() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
		this.decimalFormat = new DecimalFormat("#,##0.00", symbols);
	}

	public static void main(String[] args) {
		SpringApplication.run(DesafioFuncionariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Insere todos os funcionários ---
		funcionarioService.inserirFuncionarios();
		System.out.println("--- Funcionários inseridos ---");

		//Remove o funcionário “João” da lista ---
		funcionarioService.removerFuncionario("João");
		System.out.println("\n--- Funcionário 'João' removido ---");

		//Imprime todos os funcionários com todas suas informações ---
		System.out.println("\n--- Lista de funcionários ---");
		List<Funcionario> todosFuncionarios = funcionarioService.getFuncionarios();
		imprimirFuncionarios("Lista completa", todosFuncionarios);

		// Aplica aumento de 10% no salário ---
		funcionarioService.aplicarAumentoSalario(new BigDecimal("10"));
		System.out.println("\n--- Salários atualizados com 10% de aumento ---");

		// Imprime a lista novamente
		List<Funcionario> funcionariosComAumento = funcionarioService.getFuncionarios();
		imprimirFuncionarios("Lista Após o Aumento", funcionariosComAumento);

		// Agrupa e imprime funcionários por função ---
		System.out.println("\n--- Funcionários Agrupados por Função ---");
		Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarioService.agruparPorFuncao();

		// Itera sobre o mapa e imprime cada grupo
		funcionariosPorFuncao.forEach((funcao, listaDeFuncionarios) -> {
			imprimirFuncionarios("Função: " + funcao, listaDeFuncionarios);
		});

		// Imprime funcionários aniversariantes dos meses 10 e 12 ---
		System.out.println("\n--- Aniversariantes de Outubro e Dezembro ---");
		List<Funcionario> aniversariantes = funcionarioService.getAniversariantesDoMes(10, 12);
		imprimirFuncionarios("Aniversariantes", aniversariantes);

		// Imprime o funcionário com a maior idade ---
		System.out.println("\n--- Funcionário com Maior Idade ---");
		Optional<Funcionario> maisVelhoOpt = funcionarioService.getFuncionarioMaisVelho();

		maisVelhoOpt.ifPresent(funcionario -> {
			int idade = Period.between(funcionario.getDataNascimento(), LocalDate.now()).getYears();
			System.out.printf("Nome: %s | Idade: %d anos%n", funcionario.getNome(), idade);
		});

		// Imprime a lista de funcionários por ordem alfabética ---
		System.out.println("\n--- Lista de Funcionários em Ordem Alfabética ---");
		List<Funcionario> funcionariosOrdenados = funcionarioService.getFuncionariosOrdenadosPorNome();
		imprimirFuncionarios("Ordem Alfabética", funcionariosOrdenados);

		//Imprime o total dos salários dos funcionários ---
		System.out.println("\n--- Total dos Salários ---");
		BigDecimal totalSalarios = funcionarioService.getTotalSalarios();
		System.out.printf("O valor total da folha de pagamento é: R$ %s%n", decimalFormat.format(totalSalarios));

		// Imprime quantos salários mínimos ganha cada funcionário ---
		System.out.println("\n--- Salários em Relação ao Salário Mínimo (R$1.212,00) ---");
		final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

		for (Funcionario f : todosFuncionarios) {
			BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
			System.out.printf("%s ganha %s salários mínimos.%n", f.getNome(), decimalFormat.format(salariosMinimos));
		}
	}


	private void imprimirFuncionarios(String titulo, List<Funcionario> funcionarios) {
		System.out.println("----- " + titulo + " -----");

		System.out.printf("%-15s | %-15s | %-15s | %-15s%n", "Nome", "Data Nasc.", "Salário", "Função");
		System.out.println(new String(new char[70]).replace("\0", "-"));

		for (Funcionario f : funcionarios) {
			System.out.printf("%-15s | %-15s | %15s | %-15s%n",
					f.getNome(),
					f.getDataNascimento().format(dateFormatter),
					decimalFormat.format(f.getSalario()),
					f.getFuncao()
			);
		}
	}
}