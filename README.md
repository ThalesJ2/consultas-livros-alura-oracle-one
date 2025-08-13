# LiterAlura 📚

Este projeto é parte do **desafio proposto pela Alura em parceria com a Oracle Next Education (ONE)**, com o objetivo de criar um **Catálogo de Livros** interativo.  
Embora o desafio original tenha como foco a interação via **CLI (console)**, nesta implementação utilizei **Spring Boot** para criar rotas HTTP.

---

## CheckList do challenge

O desafio consiste em desenvolver um catálogo de livros que:

- Busque dados de uma API pública de livros.
- Faça o consumo e análise de dados em **JSON**.
- Armazene as informações em um **banco de dados**.
- Ofereça no mínimo **5 opções de interação** com o usuário.
- Permita **consultas e filtros** por livros e autores.

---

##  Endpoints Implementados

### `GET /books`

Este endpoint retorna todos os livros cadastrados no banco de dados juntamente com seus respectivos autores, seguindo o relacionamento **1:N** requirido na descrição do challenge.

#### Exemplo de resposta

```json
[
    {
        "id": 2701,
        "title": "Moby Dick; Or, The Whale",
        "language": "en",
        "copyright": false,
        "media_type": "Text",
        "download_count": 115502,
        "author": {
            "id": 1,
            "name": "Melville, Herman",
            "birth_year": 1819,
            "death_year": 1891
        }
    }
]
```
- Ao acessar a rota **`/books`**, o sistema consulta a **API Gutendex** para buscar a **primeira página de livros disponíveis**.

- Os livros retornados pela API são **salvos automaticamente no banco de dados**.


### `GET /books?title=`
- É possível utilizar um **query parameter `title`** para buscar um livro específico pelo título.  



- Se o livro já existir no banco de dados, ele será retornado diretamente.
- Caso não exista, o sistema buscará o livro na **API Gutendex**, salvará no banco e retornará o objeto.

#### Exemplo de resposta para `/books?title=Dom Casmurro`

```json
{
  "id": 55752,
  "title": "Dom Casmurro",
  "language": "pt",
  "copyright": false,
  "media_type": "Text",
  "download_count": 1615,
  "author": {
      "id": 28,
      "name": "Machado de Assis",
      "birth_year": 1839,
      "death_year": 1908
  }
}
```

### `GET /books/{language}`

Este endpoint retorna **estatísticas sobre os livros cadastrados** no banco de dados, com base no idioma especificado como **path parameter**.


- Ao acessar a rota **`/books/{language}`**, o sistema verifica no banco de dados todos os livros que possuem o idioma informado.
- Retorna a **quantidade total de livros** encontrados naquele idioma.

#### Exemplo de resposta para `/books/en`

```json
{
    "quantity": 27,
    "language": "en"
}
```

### `GET /authors`

Este endpoint retorna **todos os autores cadastrados** no banco de dados.  

- os autores são cadastrados automaticamente junto com os livros quando os livros são adicionados.

#### Exemplo de resposta `/authors` 

```json
[
    {
        "id": 1,
        "name": "Melville, Herman",
        "birth_year": 1819,
        "death_year": 1891
    },
    {
        "id": 2,
        "name": "Forster, E. M. (Edward Morgan)",
        "birth_year": 1879,
        "death_year": 1970
    },
    {
        "id": 3,
        "name": "Austen, Jane",
        "birth_year": 1775,
        "death_year": 1817
    }
]
```
### `GET /authors/{year}`

Este endpoint retorna todos os autores que **estavam vivos em um determinado ano**, utilizando o **path parameter `year`**.



- Ao acessar a rota **`/authors/{year}`**, o sistema consulta o banco de dados e retorna apenas os autores cujo **ano de nascimento seja menor ou igual ao ano informado** e cujo **ano de falecimento seja maior ou igual ao ano informado** (ou nulo, caso o autor ainda esteja vivo).  
- Permite descobrir quais autores estavam vivos em um período específico.


```json
[
    {
        "id": 2,
        "name": "Forster, E. M. (Edward Morgan)",
        "birth_year": 1879,
        "death_year": 1970
    },
    {
        "id": 8,
        "name": "Von Arnim, Elizabeth",
        "birth_year": 1866,
        "death_year": 1941
    },
    {
        "id": 13,
        "name": "Wodehouse, P. G. (Pelham Grenville)",
        "birth_year": 1881,
        "death_year": 1975
    }
]
```

##  Tecnologias Utilizadas

- **Java** (Spring Boot)
- **JPA / Hibernate**
- **SGBD** (Postgres)
- **Docker**
- **Integração com API externa** para busca de livros

---

##  Como Rodar o Projeto

Para executar este projeto, você precisa ter instalado no seu computador:

- **Java 8 ou superior**  
- **Docker** (recomendado para facilitar a configuração do banco de dados)

### Passos para rodar

1. **Clone o repositório:**

```bash
git clone https://github.com/ThalesJ2/consultas-livros-alura-oracle-one
cd consultas-livros-alura-oracle-one
```

## 🐳 Configuração via Docker Compose

O projeto já inclui um arquivo **`docker-compose.yml`**, facilitando a execução do banco de dados e da aplicação.  

### Passos

1. **Adicionar variáveis de ambiente**

- Crie um arquivo **`.env`** dentro da pasta `src/main/resources/` com as variáveis de ambiente do banco de dados.  
  Exemplo:

```env
DB_URL=jdbc:postgres://localhost:5432/literalura
DB_USERNAME=root
DB_PASSWORD=senha123
```

- Depois de configurar as variaveis de ambiente. Vai ate a pasta  resources do projeto (se estiver via terminal) é rode:

```bash
docker compose up -d
```
- Se não estiver pelo terminal procure na sua ide algum plugin para iniciar o docker compose

- Se quiser parar o container do postgres:

```bash
docker compose down
```
  
