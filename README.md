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
