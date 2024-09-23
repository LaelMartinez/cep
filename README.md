# Consulta de CEP com ViaCEP e Persistência de Logs

Este projeto é uma API REST desenvolvida em **Java 11** com **Spring Boot**, que consome o serviço externo de consulta de CEPs do [ViaCEP](https://viacep.com.br/) e persiste as consultas realizadas em um banco de dados, utilizando **JPA** com **H2 Database** (ou **MySQL** opcionalmente). 

A API possui dois endpoints:
1. `/consulta-cep/{cep}`: Consulta o CEP fornecido no ViaCEP e salva as informações no banco de dados.
2. `/lista-ceps?uf={uf}`: Retorna os últimos 20 CEPs consultados para um determinado estado.

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot**
- **Spring Web** (para criação da API REST)
- **Spring Data JPA** (para persistência de dados)
- **H2 Database** (banco de dados em memória, ou MySQL)
- **WebClient** (para consumo da API ViaCEP)

## Funcionalidades

### 1. Consulta CEP e Persistência de Logs

- **Rota**: `GET /consulta-cep/{cep}`
- **Descrição**: Faz a consulta de um CEP na API ViaCEP e persiste a consulta no banco de dados.
- **Exemplo de resposta**:
    ```json
    {
        "cep": "01001-000",
        "rua": "Praça da Sé",
        "bairro": "Sé",
        "cidade": "São Paulo",
        "uf": "SP"
    }
    ```

### 2. Listagem de Últimos CEPs Consultados por Estado

- **Rota**: `GET /lista-ceps?uf={uf}`
- **Descrição**: Retorna os 20 últimos CEPs consultados para um determinado estado.
- **Exemplo de resposta**:
    ```json
    [
        {
            "cep": "01001-000",
            "dt_hr_consulta": "2024-09-22"
        },
        {
            "cep": "01310-100",
            "dt_hr_consulta": "2024-09-21"
        }
    ]
    ```

## Estrutura do Projeto

- `CepController.java`: Classe responsável por expor os endpoints REST.
- `CepService.java`: Serviço que consome a API ViaCEP.
- `RespostaDTO.java`: Modelo para mapear a resposta da API ViaCEP.
- `Log.java`: Entidade JPA que representa o log de consultas de CEP.
- `LogRepository.java`: Repositório JPA para acesso aos dados de log.

## Pré-requisitos

- **Java 11** ou superior
- **Maven** para gerenciamento de dependências
- **H2** (H2 Database)

## Configuração

### Usando o H2 Database

O H2 é um banco de dados em memória, ideal para testes rápidos. No arquivo `src/main/resources/application.properties`, as seguintes propriedades configuram o H2:
```properties
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Executando a Aplicação

1. Clone este repositório:
    ```bash
    git clone https://github.com/LaelMartinez/cep.git
    cd cep
    ```

2. Compile e rode o projeto com o Maven:
    ```bash
    mvn spring-boot:run
    ```

3. Acesse a aplicação em `http://localhost:8080`.

### Endpoints disponíveis

- **GET** `/consulta-cep/{cep}`: Consulta um CEP na API ViaCEP e persiste no banco de dados.
- **GET** `/lista-ceps?uf={uf}`: Retorna os 20 últimos registros de consultas de um estado (UF).

## Testando a API

Utilize ferramentas como **Postman** ou **cURL** para realizar testes nas rotas:

### Exemplo de Consulta de CEP
```bash
curl -X GET "http://localhost:8080/consulta-cep/01001000"
```

### Exemplo de Listagem de CEPs por Estado (UF)
```bash
curl -X GET "http://localhost:8080/lista-ceps?uf=SP"
```
## Autor

- **Lael Siqueira Martinez Junior** - [GitHub](https://github.com/LaelMartinez)


