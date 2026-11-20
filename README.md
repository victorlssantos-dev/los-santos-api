# 🚗 Los Santos API

API RESTful para gerenciamento de usuários desenvolvida com **Java** e **Spring Boot**, contando com persistência em **PostgreSQL**, criptografia de senhas com **BCrypt** e autenticação stateless via **JWT (JSON Web Token)**.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)

---

## 📋 Sobre o Projeto

A **Los Santos API** é um projeto backend focado em boas práticas de desenvolvimento com o ecossistema Spring. A aplicação gerencia o ciclo de vida de usuários — incluindo seus respectivos endereços e telefones — garantindo integridade dos dados (validação de e-mail e CPF duplicados) e proteção de rotas sensíveis por meio de tokens JWT.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot** (Web, Data JPA, Security)
* **PostgreSQL** (Banco de dados relacional)
* **JJWT** (Geração e validação de tokens JWT)
* **BCrypt** (Criptografia de senhas)
* **Lombok** (Redução de código boilerplate)
* **Maven** (Gerenciamento de dependências)

---

## ✨ Funcionalidades

* **Cadastro de Usuários:** Registro completo com dados pessoais, lista de endereços e telefones.
* **Validação de Unicidade:** Bloqueio automático de cadastros com e-mail ou CPF já existentes (`ConflictException`).
* **Segurança de Senhas:** Armazenamento seguro utilizando hash **BCrypt** antes de persistir no banco.
* **Autenticação JWT:** Login via e-mail e senha com emissão de token `Bearer` para acesso às rotas protegidas.
* **Consulta e Exclusão:** Busca e remoção de usuários por e-mail com tratamento de registros inexistentes (`ResourceNotFoundException`).

---

## 📂 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas bem definida:

```text
src/main/java/projeto_1/Los/Santos/
├── business/                  # Regras de negócio (UsuarioService)
├── controller/                # Endpoints REST e DTOs (UsuarioController, UsuarioDTO)
├── infrastructure/
│   ├── entity/                # Entidades JPA (Usuario, Endereco, Telefone)
│   ├── exceptions/            # Exceções personalizadas (ConflictException, ResourceNotFoundException)
│   └── repository/            # Interfaces Spring Data JPA
└── security/                  # Configuração do Spring Security e filtro JWT
```

---

## 🔌 Endpoints da API

Base URL: `http://localhost:8080`

| Método | Rota | Autenticação | Descrição |
| :--- | :--- | :---: | :--- |
| `POST` | `/usuario` | Pública | Cadastra um novo usuário com endereço e telefone |
| `POST` | `/usuario/login` | Pública | Autentica o usuário e retorna o Token JWT |
| `GET` | `/usuario?email={email}` | 🔒 Bearer Token | Busca os dados de um usuário pelo e-mail |
| `DELETE` | `/usuario/{email}` | 🔒 Bearer Token | Remove um usuário do banco de dados pelo e-mail |

### Exemplo de requisição — Cadastro (`POST /usuario`)

```json
{
  "nome": "Lucas Vinicius",
  "email": "lucas@email.com",
  "cpf": "123.456.789-00",
  "senha": "minhasenha123",
  "endereco": [
    {
      "rua": "Rua Exemplo",
      "numero": 100,
      "complemento": "Apto 12",
      "cidade": "São Paulo",
      "estado": "SP",
      "cep": "01000-000"
    }
  ],
  "telefone": [
    {
      "ddd": "11",
      "numero": "999999999"
    }
  ]
}
```

### Exemplo de requisição — Login (`POST /usuario/login`)

```json
{
  "email": "lucas@email.com",
  "senha": "minhasenha123"
}
```

> **Dica:** Copie o token retornado no `/usuario/login` e envie-o no cabeçalho `Authorization: Bearer <seu-token>` para testar os endpoints protegidos (`GET` e `DELETE`).

---

## 🚀 Como Executar o Projeto Localmente

### Pré-requisitos
* **Java 21** instalado
* **PostgreSQL** rodando localmente
* **Maven** (ou utilizar o wrapper `./mvnw` incluso no projeto)

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/victorlssantos-dev/los-santos-api.git
   cd los-santos-api
   ```

2. **Configure o banco de dados:**
   Crie um banco no PostgreSQL e ajuste as credenciais no arquivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   A API ficará disponível em `http://localhost:8080`.

---

## 👨‍💻 Autor

Desenvolvido por **Victor Lucas**

* **GitHub:** [@victorlssantos-dev](https://github.com/victorlssantos-dev)
* **E-mail:** victorlssantos.dev@gmail.com
