# Usar uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /

# Copia o arquivo JAR da aplicação para o container
COPY target/sistlab-solos-server.jar server.jar

# Define a porta que a aplicação irá expor
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "server.jar"]
