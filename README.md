# Informaçoes sobre o projeto

*Descrição:*

- API RESTful para possibilitar a leitura e manutenção da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards..

# Prerequisites

- Maven. Download link: https://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz
- Git. Download link: https://git-scm.com/download/win

# Downloading the repository

Open the Git Bash inside the directory on which you want to download the repository, and execute the following command:
```
$ git clone https://github.com/luis4129/worst-movies-api.git
```

# Building the .jar

If the previous Git Bash is still open, execute the following statement to enter the cloned repository directory:

```
$ cd worst-movies-api
```

Otherwisde, just open another Git Bash at the cloned repository directory.

After that, just execute the following command to generate the `.jar` file:

```
$ mvn package
```

# Running the .jar

Run the following statement to enter the `.jar` directory:

```
$ cd target
```

Now, all that's left is to run the `.jar` file with the statement below:

```
$ java -jar worstmovies-0.0.1-SNAPSHOT.jar
```

# Done!
You can now request the following URL's:
- List every movie: (GET) http://localhost:8080/movies;
- List a movie based on it's ID: (GET) http://localhost:8080/movies/{id};
- List the winners (movies) of a certain year: (GET) http://localhost:8080/movies/years/{year}/winners;
- List the years on which multiple movies were winners: (GET) http://localhost:8080/movies/years/multipleWinners;
- List the studios ordering by highest number of wins: (GET) http://localhost:8080/studios;
- List the highest and lowest interval between producer wins: (GET) http://localhost:8080/producers/intervals/highestAndLowest
- Excluir um filme: (DELETE) http://localhost:8080/movies/{id};
