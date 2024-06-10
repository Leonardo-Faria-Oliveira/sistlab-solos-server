# Usar uma imagem base do Maven para construir o projeto
FROM maven:3.8.5-openjdk-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Baixar as dependências do Maven
RUN mvn dependency:go-offline

# Copiar o código fonte da aplicação para o diretório de trabalho
COPY src ./src

# Construir a aplicação (gera o JAR)
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado na fase de construção anterior para o diretório de trabalho
COPY --from=build /app/target/sistlabsolos-0.0.1-SNAPSHOT.jar app.jar

CMD apt-get update -y

# Expor a porta que a aplicação irá usar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
