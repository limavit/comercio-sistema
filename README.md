# Sistema de Comércio Local

## Sobre o Projeto

Este é um sistema de gestão comercial desenvolvido como parte de um projeto de pair programming com a ferramenta **Aider**. O sistema foi projetado para gerenciar operações comerciais básicas, incluindo cadastro de empresas, produtos, clientes, fornecedores, categorias, vendas e controle de estoque.

## Arquitetura da Solução

A solução foi arquitetada por **Limavit**, seguindo os princípios de arquitetura limpa e boas práticas de desenvolvimento de software. O sistema utiliza uma estrutura modular com separação de responsabilidades entre as camadas de controle, serviço, repositório e modelo de domínio.

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação principal
- **Spring Boot**: Framework para desenvolvimento de aplicações Java
- **Spring Data JPA**: Abstração para acesso a dados
- **Hibernate**: Implementação JPA para mapeamento objeto-relacional
- **MySQL**: Banco de dados relacional
- **Maven**: Gerenciador de dependências e build
- **Lombok**: Biblioteca para redução de código boilerplate

## Estrutura do Projeto

O projeto segue a seguinte estrutura de pacotes:

- `br.com.sistema.comercio.controller`: Camada de controle com endpoints REST
- `br.com.sistema.comercio.service`: Camada de lógica de negócio
- `br.com.sistema.comercio.repository`: Camada de acesso a dados
- `br.com.sistema.comercio.model`: Entidades do domínio

## Entidades Implementadas

- **Empresa**: Cadastro de empresas com CNPJ e status
- **Usuario**: Cadastro de usuários com nome, email, empresa e perfil
- **Perfil**: Cadastro de perfis com nome
- **Cliente**: Cadastro de clientes com nome, telefone, email e empresa
- **Categoria**: Cadastro de categorias com nome e empresa
- **Produto**: Cadastro de produtos com nome, preço e categoria
- **Estoque**: Controle de estoque com quantidade, associado a Produto e Empresa
- **Fornecedor**: Cadastro de fornecedores com nome, telefone e empresa

## Evolução dos Modelos LLM

Este projeto utiliza a ferramenta **Aider** para desenvolvimento em parceria com modelos de linguagem (LLM). Durante o desenvolvimento, foram testados diferentes modelos gratuitos:

1. **deepseek-coder:6.7b** - Modelo inicial, executado localmente
2. **qwen2.5-coder:7b** - Segunda versão, também local
3. **qwen2.5-coder:3b** - Versão mais leve e rápida
4. **z-ai/glm-4.5-air:free** - Modelo atual, utilizado via API

Todos os modelos são gratuitos e a escolha foi baseada em custo-benefício, performance e disponibilidade. A ideia do projeto é demonstrar uma experiência de aprendizado com IA de baixo custo, utilizando ferramentas acessíveis para desenvolvimento de software.

## O que é o Aider?

**Aider** é uma ferramenta de linha de comando que facilita a colaboração entre desenvolvedores e modelos de linguagem. Ele permite:

- Editar código-fonte diretamente no terminal
- Manter o histórico de conversas com a IA
- Aplicar alterações específicas em arquivos
- Integrar-se com repositórios Git
- Suportar múltiplos modelos LLM

O Aider funciona como um "pair programming" digital, onde o desenvolvedor descreve o que precisa fazer e a IA sugere ou implementa as alterações diretamente no código. Esta abordagem acelera o desenvolvimento enquanto mantém o controle total sobre as decisões técnicas.

## Como o Aider auxilia no desenvolvimento

- Criação de entidades e classes
- Implementação de serviços e controladores
- Geração de repositórios
- Refatoração de código
- Correção de erros e otimizações

## Próximos Passos

- Implementação das entidades restantes (Venda, ItemVenda)
- Desenvolvimento dos endpoints REST completos
- Implementação de validações e regras de negócio
- Adição de testes unitários e de integração
- Configuração de segurança e autenticação

## Configuração do Banco de Dados

O projeto utiliza MySQL como banco de dados. As configurações de conexão estão no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bdcomercio
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Como Executar

Para executar o projeto localmente:

```bash
mvn spring-boot:run
```

O sistema estará disponível em `http://localhost:8080`.

## Contribuição

Este projeto está em desenvolvimento contínuo, com contribuições tanto do time de desenvolvimento quanto da parceria com o Aider.
