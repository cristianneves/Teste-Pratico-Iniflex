# Teste-Pratico-Iniflex
Teste de programacao em java.

Este projeto é uma aplicação de console em Java, desenvolvida com Spring Boot, como solução para um desafio prático de programação. O objetivo é gerenciar uma lista de funcionários, realizando diversas operações de inserção, remoção, cálculo e manipulação de dados, seguindo um conjunto de requisitos específicos.

A aplicação foi construída com foco em boas práticas de programação, código limpo e o uso de recursos modernos do ecossistema Java.

## Requisitos do Desafio
O projeto implementa as seguintes funcionalidades:

#### Estrutura de Classes:

Classe Pessoa com atributos nome (String) e dataNascimento (LocalDate).

Classe Funcionario que estende Pessoa, com atributos salario (BigDecimal) e funcao (String).

#### Operações Executadas:

3.1: Inserir uma lista pré-definida de funcionários.

3.2: Remover o funcionário "João" da lista.

3.3: Imprimir todos os funcionários com formatação de data (dd/mm/aaaa) e valores numéricos (padrão brasileiro).

3.4: Conceder um aumento de 10% de salário para todos os funcionários.

3.5: Agrupar os funcionários por função em uma estrutura de Map.

3.6: Imprimir os funcionários agrupados por função.

3.8: Imprimir os funcionários que fazem aniversário nos meses 10 e 12.

3.9: Imprimir o funcionário com a maior idade (exibindo nome e idade).

3.10: Imprimir a lista de funcionários em ordem alfabética.

3.11: Imprimir o total dos salários de todos os funcionários.

3.12: Imprimir quantos salários mínimos cada funcionário ganha.

### Tecnologias Utilizadas
Java 17+: Versão da linguagem utilizada.

Spring Boot: Framework principal para estruturação, injeção de dependência e configuração da aplicação.

Maven: Ferramenta de gerenciamento de dependências e build do projeto.

Lombok: Biblioteca para reduzir código boilerplate (getters, setters, construtores, etc.).

### Estrutura do Projeto
O código foi organizado seguindo uma arquitetura de serviços para separar as responsabilidades:

src/main/java/br/com/industria/desafio
├── model/
│   ├── Pessoa.java
│   └── Funcionario.java
├── service/
│   └── FuncionarioService.java
└── DesafioFuncionariosApplication.java

model: Contém as classes de domínio (entidades) da aplicação.
service: Contém a lógica de negócio e as regras de manipulação dos dados.
DesafioFuncionariosApplication: Classe principal que inicia a aplicação e orquestra a execução dos requisitos.
