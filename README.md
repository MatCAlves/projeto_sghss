# projeto_sghss

Versão das plataformas utilizadas no desenvolvimento.

Java JDK 21.0.7

Spring Boot 3.4.5

Apache Maven 3.9.6

É necessária a instalação do Apache Maven e do Java JDK para empacotamento e compilação do software.
Recomenda-se a instalação das versões utilizadas no desenvolvimento.

Uma configuração inicial é necessária para compilação e inicialização da aplicação.
No arquivo 'application.properties' deve-se inserir a senha do banco de dados MySQL instalado no sistema no campo 'spring.datasource.password'.
O campo 'spring.datasource.username' deve ser alterado caso o usuário root não esteja disponível para acesso ao SGBD.

Baixar os arquivos do projeto. Acessar sua pasta raiz e executar os seguintes comandos:

mvn package

java -jar target/sghss-0.0.1-SNAPSHOT.war

Acessar o localhost na porta 8080 através do navegador (http://localhost:8080).

O nome de usuário e senha de acesso ao projeto podem ser visualizadas e configuradas editando o arquivo classe Java 'src/main/java/br/com/sghss/SecurityConfiguration.java' acessando o método 'InMemoryUserDetailsManager'.
