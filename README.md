# Ecommerce API
[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)
## API RESTFUL feita em Java com Springboot e mariadb/mysql

## Objetivo
Projeto criado com objetivo de desenvolver minhas habilidades com springboot para criação de apis restful.



## Pré requisitos
- Mysql ou Mariadb
- Java SDK 21.05

## Configurações necessárias
### src/main/resources/application.properties
- Modificar "gerenciadorDB" para o nome do banco de dados criado na sua máquina.
- Modificar "mariadb" para "mysql" caso use esse banco de dados.
```
spring.datasource.url=jdbc:mariadb://localhost:3306/gerenciadorDB
```
- Modificar "mariadb" para "mysql" caso use esse banco de dados.
```
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
```
