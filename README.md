# xy-inc

## Requisitos necessários para rodar o projeto:

- JDK 8
- Maven
- MongoDB

> Obs: Tanto o banco de dados MongoDB(Embedded) quanto o servidor (Apache Tomcat) são embarcados e rodam no mesmo ciclo de vida da aplicação.

O MongoDB está pré configurado para rodar na porta: 27017, mas caso queira mudar as configurações iniciais, basta alterar o application.properties com um modelo parecido com o exemplo que segue abaixo.

```
spring.data.mongodb.host=mongoserver
spring.data.mongodb.port=27017
```

## O Tomcat está configurado para rodar na porta: 8080

### Executando os testes automatizados
> Na raiz do projeto execute: mvn clean test
##### O resultado esperado é a execução de 09 casos de testes com sucesso.

### Executando a aplicação
- Na raiz da aplicação execute o comando: mvn clean package 
- Na raiz da aplicação execute o comando: java -jar target/xy-inc-0.0.1-SNAPSHOT.jar

## Serviços disponíveis:

GET  - Lista todos os pontos de interesse
- http://localhost:8080/pontos-interesse/ 

POST - Cadastra novo ponto de interesse através de Payload json, conforme exemplo abaixo. 
- http://localhost:8080/pontos-interesse/ 

> Execute um a um cada objeto de json abaixo.

```json
[
  {
    "nome": "Lanchonete",
    "coordenadaX": 27,
    "coordenadaY": 12
  },
  {
    "nome": "Posto",
    "coordenadaX": 31,
    "coordenadaY": 18
  },
  {
    "nome": "Joalheria",
    "coordenadaX": 15,
    "coordenadaY": 12
  },
  {
    "nome": "Floricultura",
    "coordenadaX": 19,
    "coordenadaY": 21
  },
  {
    "nome": "Pub",
    "coordenadaX": 12,
    "coordenadaY": 8
  },
  {
    "nome": "Supermercado",
    "coordenadaX": 23,
    "coordenadaY": 6
  },
  {
    "nome": "Churrascaria",
    "coordenadaX": 28,
    "coordenadaY": 2
  }
]
```
#### Exemplo de POST inserir
![exemplopostinserrir](https://user-images.githubusercontent.com/6243119/30442341-8a3c0d84-9952-11e7-8bbe-e8ee47e59fdb.jpg)

GET - Lista todos os pontos de interesse mais próximos de acordo com as coordenadas e a dinstância passada
- http://localhost:8080/pontos-interesse/proximos?coordenadaX=20&coordenadaY=10&distancia=10
> Edite a URL na medida quer for preciso alterando apenas os parâmetros que seguem: coordenadaX=?, coordenadaY=?, distancia=?

#### Exemplo de GET com parâmetros com sucesso
![exemplogetcomparametros](https://user-images.githubusercontent.com/6243119/30442497-ff37eb62-9952-11e7-8dc8-04f5951de1e5.jpg)




