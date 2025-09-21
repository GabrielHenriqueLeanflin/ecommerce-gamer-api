# E-Commerce Gamer API

[![Status do Pipeline](https://github.com/GabrielHenriqueLeanflin/ecommerce-gamer-api/actions/workflows/main.yml/badge.svg)](https://github.com/GabrielHenriqueLeanflin/ecommerce-gamer-api/actions/workflows/main.yml)
[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-conteinerizado-blue.svg)](https://www.docker.com/)
[![AWS](https://img.shields.io/badge/AWS-implantado-orange.svg)](https://aws.amazon.com/)

API RESTful para um e-commerce de hardware e periféricos gamer, desenvolvida como um projeto completo para demonstrar habilidades em desenvolvimento backend, DevOps e Cloud.

**Acesse a documentação ao vivo da API implantada na AWS:**
**[http://54.207.176.183:8080/swagger-ui/index.html](http://54.207.176.183:8080/swagger-ui/index.html)**

---

## 🏛️ Arquitetura e Deploy (CI/CD)

Esta aplicação está implantada na **AWS** utilizando uma arquitetura desacoplada e segura:
* **Aplicação:** A API Spring Boot, conteinerizada com **Docker**, roda em uma instância **EC2**.
* **Banco de Dados:** Um banco **PostgreSQL** gerenciado pelo **Amazon RDS**, garantindo segurança e escalabilidade.
* **Acesso ao Servidor:** O acesso para manutenção é feito de forma segura via **AWS Systems Manager (SSM) Session Manager**, eliminando a necessidade de chaves SSH e mantendo a porta 22 fechada.

O processo de deploy é **100% automatizado** através de um pipeline de **CI/CD com GitHub Actions**. A cada `push` para a branch `dev_env`, o pipeline:
1.  Executa os testes automatizados.
2.  Constrói a nova imagem Docker da aplicação.
3.  Conecta-se à instância EC2 via **SSM Run Command** e atualiza a aplicação para a nova versão, sem downtime manual.

---

## 🚀 Funcionalidades

* **Autenticação e Autorização com JWT:** Sistema completo de registro e login com tokens.
* **Gerenciamento de Produtos:** CRUD completo com busca avançada, paginação e filtros.
* **Sistema de Pedidos Transacional:** Lógica de negócio para criação de pedidos com atualização de estoque.
* **Tratamento de Exceções Centralizado:** Respostas de erro padronizadas e claras.
* **Documentação Interativa:** Gerada automaticamente com **Swagger**.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem & Framework:** Java 17, Spring Boot 3
* **Persistência:** Spring Data JPA, Hibernate, PostgreSQL
* **Segurança:** Spring Security, JWT (com Auth0 java-jwt)
* **Conteinerização:** Docker, Docker Compose
* **Cloud & DevOps:** AWS (EC2, RDS, IAM, SSM), GitHub Actions
* **Build:** Maven
* **Documentação:** SpringDoc OpenAPI

---

## ⚙️ Como Rodar o Projeto Localmente

A forma mais simples de executar este projeto é utilizando Docker e Docker Compose.

### Pré-requisitos
* Instalar Docker
* Instalar Docker Compose

### Passo a Passo
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/GabrielHenriqueLeanflin/ecommerce-gamer-api.git](https://github.com/GabrielHenriqueLeanflin/ecommerce-gamer-api.git)
    cd ecommerce-gamer-api
    ```

2.  **Execute o Docker Compose:**
    Na raiz do projeto, execute o comando abaixo. Ele irá construir a imagem da API, baixar a imagem do PostgreSQL e iniciar os dois contêineres.
    ```bash
    docker-compose up --build
    ```

3.  **Acesse a aplicação:**
    * API disponível em: `http://localhost:8080`
    * Documentação Swagger: `http://localhost:8080/swagger-ui/index.html`