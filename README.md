# Sistema de Comércio Local

## Sobre o Projeto

Este é um sistema de gestão comercial desenvolvido como parte de um projeto de pair programming com a ferramenta **Aider**. O sistema foi projetado para gerenciar operações comerciais básicas, incluindo cadastro de empresas, produtos, clientes, fornecedores, categorias, vendas e controle de estoque.

## Arquitetura da Solução

A solução foi arquitetada por **Limavit**, seguindo os princípios de arquitetura limpa e boas práticas de desenvolvimento de software. O sistema utiliza uma estrutura modular com separação de responsabilidades entre as camadas de controle, serviço, repositório e modelo de domínio.

## Tecnologias Utilizadas

- **Java 17+**: Linguagem de programação principal
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
- **Produto**: Cadastro de produtos com nome, preço e categoria

## Desenvolvimento com Aider

Este projeto está sendo desenvolvido em parceria com a ferramenta **Aider**, uma inteligência artificial que auxilia no desenvolvimento de código. O Aider ajuda na:

- Criação de entidades e classes
- Implementação de serviços e controladores
- Geração de repositórios
- Refatoração de código
- Correção de erros e otimizações

## Próximos Passos

- Implementação das entidades restantes (Cliente, Fornecedor, Categoria, Venda, ItemVenda, Estoque)
- Desenvolvimento dos endpoints REST completos
- Implementação de validações e regras de negócio
- Adição de testes unitários e de integração
- Configuração de segurança e autenticação

## Como Executar

Para executar o projeto localmente:

```bash
mvn spring-boot:run
```

O sistema estará disponível em `http://localhost:8080`.

## Contribuição

Este projeto está em desenvolvimento contínuo, com contribuições tanto do time de desenvolvimento quanto da parceria com o Aider.
