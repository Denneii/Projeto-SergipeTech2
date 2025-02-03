# Vehicle Fleet API

## Descrição

Esta API permite gerenciar uma frota de veículos, classificando-os em carros e motos. A API oferece funcionalidades para cadastrar, listar, buscar, atualizar e excluir veículos no banco de dados PostgreSQL.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2
- Spring Data JPA
- PostgreSQL
- Maven

## Estrutura do Projeto

```
vehicle-fleet-api/
│-- src/
│   ├── main/
│   │   ├── java/com/xwz/fleet/
│   │   │   ├── controllers/   # Controladores da API
│   │   │   ├── models/        # Classes de modelo (Veiculo, Carro, Moto)
│   │   │   ├── repositories/  # Interfaces de acesso ao banco de dados
│   │   │   ├── services/      # Lógica de negócio
│   │   │   ├── FleetApplication.java  # Classe principal do Spring Boot
│   ├── test/  # Testes unitários e de integração
│-- pom.xml  # Configuração do Maven
│-- README.md  # Documentação do projeto
```

## Configuração do Banco de Dados

A API utiliza PostgreSQL. Configure as credenciais no arquivo `FleetApplication.java`:

```properties
url=jdbc:postgresql://localhost:5432/fleet_db
username=seu_usuario
password=sua_senha
class-name=org.postgresql.Driver
```

## Executando o Projeto

1. Instale o PostgreSQL e crie um banco de dados chamado `fleet_db`.
2. Configure o `FleetApplication.java` com suas credenciais.
3. Compile o projeto com Maven:
   ```sh
   mvn clean install
   ```
4. Inicie a aplicação:
   ```sh
   mvn spring-boot:run
   ```

## Endpoints da API

### Cadastrar um Veículo

**POST** `/veiculos`

```json
{
  "tipo": "carro",
  "modelo": "Civic",
  "fabricante": "Honda",
  "ano": 2022,
  "preco": 120000.0,
  "quantidadePortas": 4,
  "tipoCombustivel": "flex"
}
```
```json
{
  "tipo": "moto",
  "modelo": "biz",
  "fabricante": "Honda",
  "ano": 2022,
  "preco": 20000.0,
  "cilindrada": 120
}
```

### Listar Todos os Veículos

**GET** `/veiculos`

### Buscar um Veículo pelo ID

**GET** `/veiculos/{id}`
```json
{
    "tipo": "carro",
    "modelo": "Civic",
    "fabricante": "Honda",
    "ano": 2022,
    "preco": 120000.0,
    "quantidadePortas": 4,
    "tipoCombustivel": "flex"
}
```
Atualizar um Veículo

**PUT** `/veiculos/{id}`

```json
{
  "tipo": "carro",
  "modelo": "Civic",
  "fabricante": "Honda",
  "ano": 2023,
  "preco": 125000.0,
  "quantidadePortas": 4,
  "tipoCombustivel": "flex"
}
```

### Deletar um Veículo

**DELETE** `/veiculos/{id}`

## DDL

Este é o DDL do banco de dados.
``` sql
CREATE TABLE Veiculo (
    id SERIAL PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    fabricante VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Carro (
    id INT PRIMARY KEY REFERENCES Veiculo(id),
    quantidade_portas INT NOT NULL,
    tipo_combustivel VARCHAR(255) NOT NULL
);

CREATE TABLE Moto (
    id INT PRIMARY KEY REFERENCES Veiculo(id),
    cilindrada INT NOT NULL
);
```

## Licença

Este projeto está sob a licença MIT.

