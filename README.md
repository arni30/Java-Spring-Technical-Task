### Java-Spring-Technical-Task

### Usage:

```
1. git clone https://github.com/arni30/Java-Spring-Technical-Task
2. If Docker installed:
    ./mvnw clean package && docker build -t techtask . && docker run techtask
   If java11 installed:
    ./mvnw clean package spring-boot:run
3. Open localhost:8080/swagger-ui/
    POST /api/account/create   for create account
    GET /api/account/balance   for get balance
    PUT /api/account/deposit   for deposit to account
    PUT /api/account/withdraw  for withdraw from account
    PUT /api/account/transfer  for transfer between accounts
    The examples of the requests are in swagger
```
