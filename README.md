**CEP Service**
===========
**Tecnologias utilizadas:**
- Java 8
- Spring Boot
- Maven
- Spring Initializer para scaffolding do projeto: http://start.spring.io/starter.zip?type=maven-project&bootVersion=1.4.2.RELEASE&baseDir=cep-service&groupId=br.com.cidade&artifactId=cep-service&name=cep-service&description=Servi%C3%A7o+para+consulta+de+endere%C3%A7os&packageName=br.com.cidade&packaging=war&javaVersion=1.8&language=java&autocomplete=&style=web&style=freemarker&generate-project=
- Por ser uma aplicação spring-boot pode ser usada em um container como Tomcat ou diretamente na linha comando
- Para teste utilize o comando `mvn test spring-boot:run`, para que sejam executados os testes de integração e a aplicação permaneça
   rodando na porta 8080 por padrão.

**CRUD Service**
===========
**Tecnologias utilizadas:**
- Java 8
- Spring Boot
- Mysql
- H2
- Maven
- Spring Initializer para scaffolding do projeto: http://start.spring.io/starter.zip?type=maven-project&bootVersion=1.4.2.RELEASE&baseDir=cep-service&groupId=br.com.cidade&artifactId=cep-service&name=cep-service&description=Servi%C3%A7o+para+consulta+de+endere%C3%A7os&packageName=br.com.cidade&packaging=war&javaVersion=1.8&language=java&autocomplete=&style=web&style=freemarker&generate-project=
- Por ser uma aplicação spring-boot pode ser usada em um container como Tomcat ou diretamente na linha comando
- Existem dois profiles configurados no maven, que também alteram o profile do spring; O profile development, ativo por padrão, utiliza base de dados virtual H2; O profile production utiliza um banco de dados mysql, configurado local, é possivel alterar os dados de conexão dentro da pasta do profile. Existe um arquivo db.sql para auxiliar na criação das tabelas
- Para teste utilize o comando `mvn test spring-boot:run`, para que sejam executados os testes de integração e a aplicação permaneça executando na porta 8080 por padrão.

**Stream Validator**
===========
**Tecnologias utilizadas:**
- Java 8
- Para testar utilize o jar pré compilado com java -jar stream-validator.jar STREAM_A_SER_VALIDADO


**O que acontece quando acesso uma URL?**
===========
Quando acesso uma URL, é gerada uma requisição HTTP que segue basicamente o seguinte caminho:
	1. O endereço é convertido para o IP corresponde por um serviço DNS
	2. A requisição pode cair em uma camada de cache e retornar o conteudo para o usuário imediatamente
	3. Caso não encontre cache, a requisição chega à um balanceador de carga, um serviço responsável por distribuir requisições entre um ou mais servidores, e é direcionada à um dos servidores
	4. Chegando ao servidor/container a requisição é tratada para o que o componente responsável por responder aquela URL seja encontrado. Caso não seja encontrado geralmente é retornada uma resposta com status 404, pode ser encontrado também um redirecionamento 3xx.
	5. Encontrado o componente o código Server-Side é executado, ou apenas um arquivo é "servido" e uma resposta é enviada de volta ao navegador do usuário.
	6. De volta ao navegador do usuário ainda é possivel que seja executado código Client-Side, até que a completa renderização da paǵina seja concluída