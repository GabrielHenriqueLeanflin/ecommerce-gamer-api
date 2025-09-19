# E-Commerce Gamer API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green)
![Status](https://img.shields.io/badge/status-concluído-brightgreen)

API RESTful para um e-commerce de hardware e periféricos gamer, desenvolvida como um projeto completo para demonstrar habilidades em desenvolvimento backend com o ecossistema Spring. O projeto inclui funcionalidades como autenticação baseada em tokens, gerenciamento de pedidos com lógica de negócio transacional e uma arquitetura robusta com tratamento de erros centralizado.

---

## 🚀 Funcionalidades Implementadas

* **Autenticação e Autorização:**
    * Sistema completo de registro e login de usuários.
    * Autenticação stateless utilizando **JWT (JSON Web Tokens)**.
    * Armazenamento seguro de senhas com criptografia.
    * Filtros de segurança para proteger endpoints específicos.


* **Gerenciamento de Produtos:**
    * CRUD completo para produtos.
    * Busca avançada com **paginação**, **ordenação** e **filtros** dinâmicos por nome, categoria e faixa de preço.


* **Sistema de Pedidos:**
    * Endpoint para usuários autenticados criarem novos pedidos.
    * Lógica de negócio **transacional** para garantir a consistência dos dados, incluindo a **redução de estoque** dos produtos no momento da compra.
    * Endpoints para consultar pedidos (detalhes de um pedido específico e todos os pedidos do usuário logado).
    * Funcionalidade para **atualizar o status** de um pedido.


* **Validação de Requests, Tratamento de Erros e Respostas:**
    * **Respostas de API Padronizadas:** Todas as respostas (sucesso e erro) seguem um formato padrão para facilitar a visualização de quem estar consumindo.
    * **Validação de Dados:** Uso do Spring Validation para garantir a integridade dos dados de entrada.
    * **Tratamento de Exceções Centralizado:** Uma captura global de exceções, de recursos não encontrados e de validação, retornando respostas de erro claras e com os status HTTP corretos.


* **Documentação:**
    * Documentação interativa e completa da API gerada automaticamente com **Swagger (springdoc-openapi)**.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.2.5
* **Segurança:** Spring Security
* **Banco de Dados:** Spring Data JPA / Hibernate
* **Persistência:** MySQL
* **Autenticação:** JWT (JSON Web Tokens) - Biblioteca java-jwt da Auth0
* **Validação:** Spring Boot Starter Validation
* **Documentação:** SpringDoc OpenAPI (Swagger 3)
* **Gerenciamento de Pacotes:** Maven
* **Utilitários:** Lombok

---

## 📄 Documentação da API (Swagger)

A documentação completa e interativa dos endpoints está disponível através do Swagger UI. Após iniciar a aplicação, acesse o seguinte link no seu navegador:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ⚙️ Como Configurar e Rodar o Projeto Localmente?

## OPÇÃO 1
## 🐳 Rodando com Docker (Recomendado)

A forma mais simples de executar este projeto é utilizando Docker e Docker Compose.

### Pré-requisitos
* Docker
* Docker Compose

### Passo a Passo
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd ecommerce-gamer-api
    ```

2.  **Copie o .env.example e insira alguma valor de token:**
    Na raiz do projeto execute o comando abaixo no terminal e preencha o .env
    ```bash
    copy .env.example .env
    ```
    
3. **Execute o Docker Compose:**
    Na raiz do projeto, execute o seguinte comando no terminal. Ele irá construir a imagem da API, baixar a imagem do MySQL e iniciar os dois containers.
    ```bash
    docker-compose up --build
    ```

## OPÇÃO 1
## Instalando tudo local (Não Recomendado)
A API estará disponível em `http://localhost:8080` e o banco de dados em `localhost:3306`.


Siga os passos abaixo para executar o projeto na sua máquina.

### Pré-requisitos
* JDK 17 ou superior
* Maven 3.8 ou superior
* MySQL 8

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd ecommerce-gamer-api
    ```

2.  **Configure o Banco de Dados:**
    * Crie um banco de dados no seu MySQL chamado `ecommerce_db`.
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com o seu usuário e senha do MySQL.

3.  **Configure o Segredo do JWT:**
    * No mesmo arquivo `application.properties`, altere o valor da propriedade `api.security.token.secret` para uma frase secreta de sua escolha.

4.  **Execute a Aplicação:**
    * Pelo terminal, na raiz do projeto, execute o comando Maven:
        ```bash
        mvn spring-boot:run
        ```
    * Alternativamente, você pode executar a classe `EcommerceGamerApiApplication.java` diretamente pela sua IDE (IntelliJ, Eclipse, etc.).

A API estará disponível em `http://localhost:8080`.

---

## 📦 Exemplo de Uso (cURL)

**1. Registrar um novo usuário:**
```bash
curl -X POST http://localhost:8080/auth/register \
-H "Content-Type: application/json" \
-d '{
    "name": "Seu Nome",
    "email": "seu@email.com",
    "password": "sua_senha"
}'
```

**2. Fazer login para obter um token:**
```bash
curl -X POST http://localhost:8080/auth/login \
-H "Content-Type: application/json" \
-d '{
    "email": "seu@email.com",
    "password": "sua_senha"
}'
```

**3. Criar um pedido (rota protegida):**
```bash
# Substitua <SEU_TOKEN_JWT_AQUI> pelo token recebido no login
curl -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-H "Authorization: <SEU_TOKEN>" \
-d '{
    "items": [
        {"productId": 1, "quantity": 1},
        {"productId": 5, "quantity": 2}
    ]
}'
```
