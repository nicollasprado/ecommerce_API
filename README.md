# Ecommerce Restful API [![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity) [![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://lbesson.mit-license.org/)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

## Objetivo
Projeto criado com objetivo de desenvolver minhas habilidades com springboot para criação de apis restful.

## Pré requisitos
- [Mysql server](https://dev.mysql.com/downloads/mysql/) ou [Mariadb server](https://mariadb.org/download/?t=mariadb&p=mariadb&r=11.6.2)
- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

## Configurações necessárias
### src/main/resources/application.properties
- Modificar "gerenciadorDB" para o nome do banco de dados criado na sua máquina.
- Modificar "mariadb" para "mysql" caso use esse banco de dados.
```
spring.datasource.url=jdbc:mariadb://localhost:3306/gerenciadorDB?createDatabaseIfNotExist=true
```
- Modificar "mariadb" para "mysql" caso use esse banco de dados.
```
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
```

## Consumindo a API
#### Método GET
[x] localhost:8080/user/{id} <br>
- Retorna em json o usuário do id informado.<br>
[x] localhost:8080/product/{id}<br>
- Retorna em json o produto do id informado.<br>

#### Método POST
[x] localhost:8080/user
 - Cria um usuário com base no json enviado.
[x] localhost:8080/product
 - Cria um produto com base no json enviado.

### Método PUT
[x] localhost:8080/user/{id}
 - Edita o usuário com o id escolhido e com base no json enviado.
[x] localhost:8080/product/{id}
 - Edita o produto com o id escolhido e com base no json enviado.
[x] localhost:8080/cart/adicionar/{userId}/{productId}/{productQuantidade}
 - Aumenta a quantidade do produto especificado do carrinho do usuario especificado.
[x] localhost:8080/cart/diminuir/{userId}/{productId}/{productQuantidade}
 - Diminui a quantidade do produto especificado do carrinho do usuario especificado.

### Método DELETE
[x] localhost:8080/user/{id}
 - Remove o usuário do id informado.
[x] localhost:8080/product/{id}
 - Remove o produto do id informado.
[x] localhost:8080/cart/{userId}/{productId}
 - Remove o produto informado do carrinho do usuário informado.
