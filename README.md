# Informaçoes sobre o projeto

*Descrição:*

- API RESTful para possibilitar a leitura e manutenção da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards..

# Pré-requisitos

- Maven. Link para download: https://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz
- Git. Link para downlaod: https://git-scm.com/download/win

# Baixando o repositório

Abra o Git Bash no diretório que deseja baixar o repositório e digite o comando abaixo:
- $ git clone https://github.com/luis4129/worst-movies-api.git

# Gerando o JAR

Caso ainda estiver com o Git Bash utilizado anteriormente, execute o comando abaixo para entrar no diretório do repositório clonado:
- $ cd worst-movies-api

Caso contrário, basta abrir outro Git Bash no diretório do repositório clonado.

Após isso, basta executar o comando abaixo para gerar o JAR do repositório (Nesse momento os testes de integração serão executados):
- $ mvn package

# Executando os testes de integração manualmente (Opcional)

Caso deseje rodar os testes de integração separadamente, basta rodar o comando abaixo:
- $ mvn test

# Executando o JAR

Agora só falta executar, execute o comando abaixo para entrar no diretório do JAR criado:
- $ cd target

Agora basta executar o JAR, com o comando abaixo:
- $ java -jar worstmovies-0.0.1-SNAPSHOT.jar

# Pronto! Agora é só consumir os end-points abaixo:
- Listar todos os filmes: (GET) http://localhost:8080/movies;
- Listar um filme correspondente a um ID: (GET) http://localhost:8080/movies/{id};
- Listar os filmes vencedores de um ano específico: (GET) http://localhost:8080/movies/years/{year}/winners;
- Listar anos que tiveram mais de um filme vencedor: (GET) http://localhost:8080/movies/years/multipleWinners;
- Listar estudios por ordem de contagem de vitórias: (GET) http://localhost:8080/studios;
- Listar maior e menos intervalo entre vitórias dentre os produtores: (GET) http://localhost:8080/producers/intervals/highestAndLowest
- Excluir um filme: (DELETE) http://localhost:8080/movies/{id};
