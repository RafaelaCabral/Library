# 📚 Library

**Library** é um sistema de gerenciamento de biblioteca desenvolvido em Kotlin. Ele permite o cadastro de clientes, editoras, idiomas, livros (catálogos), inventário, empréstimos e muito mais.

---

## 🔧 Tecnologias Utilizadas

- **Kotlin**
- **PostgreSQL**
- **Docker**
- **Gradle**


---

## 🗃️ Estrutura do Banco de Dados

O banco de dados foi projetado para representar os principais elementos de uma biblioteca tradicional.

### 📁 Tabelas principais

#### `client`
Armazena os dados dos clientes da biblioteca.

| Campo              | Tipo           | Descrição                     |
|--------------------|----------------|-------------------------------|
| client_id          | CHAR(26)       | Identificador único (ex: ULID) |
| client_name        | VARCHAR(100)   | Nome do cliente               |
| client_email       | VARCHAR(100)   | Email (único)                 |
| client_phone       | VARCHAR(14)    | Telefone                      |
| client_created_at  | TIMESTAMP      | Data de criação               |
| client_updated_at  | TIMESTAMP      | Última atualização            |

---

#### `publisher`
Editoras dos livros cadastrados.

| Campo                | Tipo           | Descrição             |
|----------------------|----------------|-----------------------|
| publisher_id         | SERIAL         | Identificador único   |
| publisher_name       | VARCHAR(255)   | Nome da editora       |
| publisher_created_at | TIMESTAMP      | Data de criação       |
| publisher_updated_at | TIMESTAMP      | Última atualização    |

---

#### `language`
Idiomas disponíveis nos livros.

| Campo                | Tipo           | Descrição            |
|----------------------|----------------|----------------------|
| language_id          | SERIAL         | Identificador único  |
| language_name        | VARCHAR(50)    | Nome do idioma       |
| language_created_at  | TIMESTAMP      | Data de criação      |
| language_updated_at  | TIMESTAMP      | Última atualização   |

---

#### `catalog`
Tabela central que representa os livros da biblioteca.

| Campo                 | Tipo            | Descrição                      |
|-----------------------|-----------------|--------------------------------|
| catalog_id            | CHAR(13)        | Código do livro (ex: ISBN)     |
| catalog_title         | VARCHAR(255)    | Título do livro                |
| catalog_author        | VARCHAR(100)    | Autor do livro                 |
| catalog_year          | INT             | Ano de publicação              |
| catalog_version       | INT             | Edição do livro                |
| publisher_id          | INT             | FK da editora                  |
| catalog_pages         | INT             | Total de páginas               |
| catalog_synopsis      | VARCHAR(600)    | Sinopse                        |
| catalog_is_foreign    | BOOLEAN         | Livro estrangeiro (true/false)|
| language_id           | INT             | FK do idioma                   |
| catalog_created_at    | TIMESTAMP       | Data de criação                |
| catalog_updated_at    | TIMESTAMP       | Última atualização             |

---

#### `genrer`
Gêneros literários (ex: Ficção, Romance, etc).

| Campo               | Tipo           | Descrição           |
|---------------------|----------------|---------------------|
| genrer_id           | SERIAL         | Identificador único |
| gendrer_value       | VARCHAR(50)    | Nome do gênero      |
| genrer_created_at   | TIMESTAMP      | Data de criação     |
| genrer_updated_at   | TIMESTAMP      | Última atualização  |

---

#### `catalogGenrer`
Relaciona livros a seus gêneros (N:N).

| Campo                    | Tipo        | Descrição                     |
|--------------------------|-------------|-------------------------------|
| catalog_id               | CHAR(13)    | FK do livro                   |
| genrer_id                | INT         | FK do gênero                  |
| catalog_genrer_created_at| TIMESTAMP   | Data de criação               |
| catalog_genrer_updated_at| TIMESTAMP   | Última atualização            |

---

#### `inventory`
Representa cada exemplar físico disponível para empréstimo.

| Campo                | Tipo        | Descrição                    |
|----------------------|-------------|------------------------------|
| inventory_id         | CHAR(26)    | Identificador único (ULID)   |
| inventory_condition  | INT         | Estado de conservação (0–100)|
| inventory_is_available | BOOLEAN   | Disponível para empréstimo   |
| inventory_created_at | TIMESTAMP   | Data de criação              |
| inventory_updated_at | TIMESTAMP   | Última atualização           |
| catalog_id           | CHAR(13)    | FK do catálogo (livro)       |

---

#### `loan`
Registra os empréstimos realizados pelos clientes.

| Campo             | Tipo        | Descrição                         |
|-------------------|-------------|-----------------------------------|
| loan_id           | CHAR(26)    | Identificador único (ULID)        |
| loan_days_to_expire | INT       | Dias até a devolução              |
| loan_returned_at  | TIMESTAMP   | Data de devolução (se houver)     |
| loan_created_at   | TIMESTAMP   | Data de criação                   |
| loan_updated_at   | TIMESTAMP   | Última atualização                |
| client_id         | CHAR(26)    | FK do cliente                     |
| inventory_id      | CHAR(26)    | FK do exemplar emprestado         |

---

## 🚧 Funcionalidades

- [x] Cadastro e listagem de clientes
- [ ] Registro de editoras e idiomas
- [ ] Cadastro de livros com múltiplos gêneros
- [ ] Controle de inventário de exemplares
- [ ] Empréstimo e devolução de livros

---

## 📦 Como rodar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/RafaelaCabral/Library.git
cd Library
```

### 2. Suba o banco de dados com Docker

```bash
docker compose up -d
```

> Verifique se o arquivo `application.conf` ou `.env` está com os dados corretos para conectar ao PostgreSQL.

### 3. Execute a aplicação

```bash
./gradlew run
```

---

## 🧪 Testes

> *Ainda será implementado*

---

## 📌 Observações

- O projeto utiliza **ULID** para identificação de entidades como `client`, `inventory` e `loan`, garantindo ordenação e unicidade.
- Campos de data são automaticamente preenchidos com `CURRENT_TIMESTAMP` no banco de dados.

---

## 👩‍💻 Desenvolvido por

**Rafaela Vieira**  
[GitHub](https://github.com/RafaelaCabral) • [LinkedIn](https://www.linkedin.com/in/rafaela-vieira-733b5922a/)
