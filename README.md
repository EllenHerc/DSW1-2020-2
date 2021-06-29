# Java Spring Boot Rest API

Rest API desenvolvida utilizando Java Spring Boot, durante a disciplina de desenvolvimento de software para 1, departamento de computação, UFSCar. A comunicação com a API é realizada através de JSON.

## Tecnologias Utilizadas:
- [Java](https://www.oracle.com/br/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySql](https://www.mysql.com/)
- [Spring Boot](https://spring.io/projects/spring-boot)

## Endpoints (Usuários):

### Listar todos os usuários: 
> GET "/api/v1/usuario"
### Listar usuário por Id:
> GET "/api/v1/usuario/id"
> id: id do usuário
### Criar usuário:
> POST "/api/v1/usuario"
> Exemplo JSON: {
  "email": "email@email.com",
  "senha": "minino 6 caracteres",
  "papel": "ADMIN"
}
### Atualizar usuário:
> PATCH "/api/v1/usuario/id"
> id: id do usuário
> Exemplo JSON: {
  "papel": "CLIENT"
}
### Remover usuário:
> DELETE "/api/v1/usuario/id"
> id: id do usuário

## Endpoints (Imobiliárias):

### Listar todos as imobiliárias:
> GET "/api/v1/imobiliaria"
### Listar imobiliária por Id:
> GET "/api/v1/imobiliaria/id"
> id: id da imobiliaria
### Criar imobiliária:
> POST "/api/v1/imobiliaria"
> Exemplo JSON: {
  "email": "email@email.com",
  "senha": "senhaExemplo",
  "descricao": "ImoBil Top",
	"nome": "Imobil",
	"cnpj": "12345678911"        
}
### Atualizar imobiliária:
> PATCH "/api/v1/imobiliaria/id"
> id: id da imobiliaria
> Exemplo JSON: {
    "nome": "novo nome",
}
### Remover imobiliária:
> DELETE "/api/v1/imobiliaria/id"
> id: id da imobiliaria

## Endpoints (Imóveis):

### Listar todos os imóveis:
> GET "/api/v1/imovel"
### Listar imóveil por Id:
> GET "/api/v1/imovel/id"
> id: id do imovel
### Listar imóveis por cidade:
> GET "/api/v1/imovel/filtros?cidade=nomeDaCidade"
> cidade: nome da cidade
### Listar imóveis por imobiliária:
> GET "/api/v1/imovel/imobiliaria/id"
> id: id da imobiliaria

## Instruções para rodar o projeto:
- Certifique-se de possuir todas as tecnologias envolvendo o projeto devidamente instaladas e configuradas (incluindo o banco de dados configurado com as credenciais  de acesso corretas, de acordo como indicado no arquivo **application.properties**).
- Rodar o script de banco de dados localizado na pasta **DBScripts**.
- Para iniciar o projeto pela linha de comando, a partir da pasta raiz, rode: **mvn spring-boot:run**; ou o comando equivalente, a depender do sistema operacional.
- Obs: o arquivo **Endpoints.json** contém exemplos de utilização de todos os endpoints do projeto; podendo, este arquivo, ser importado em softwares como Insomnia ou Postman.

## Autores:
- 760722 Caio Cesar de Morais Sales
- 743528 Ellen Cristina Herculano