# Estágio 1: Construção do projeto Java
FROM maven:3.8.3-openjdk-17 AS DEPS

WORKDIR /build

# Copiar arquivos de configuração Maven
COPY pom.xml pom.xml
COPY entrypoints/pom.xml ./entrypoints/
COPY dataproviders/pom.xml ./dataproviders/
COPY core/pom.xml ./core/
COPY core/domain/pom.xml ./core/domain/
COPY core/usecase/pom.xml ./core/usecase/

# Resolve as dependências
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

# Copie as dependências do estágio DEPS com a vantagem
# de usar o cache de camadas do Docker. Se algo der errado a partir desta
# linha, todas as dependências do estágio DEPS já foram baixadas e
# armazenadas nas camadas do Docker.
FROM maven:3.8.3-openjdk-17 AS BUILDER

WORKDIR /build

COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /build /build
COPY entrypoints/src /build/entrypoints/src
COPY dataproviders/src /build/dataproviders/src
COPY core /build/core

# Compila o projeto
RUN mvn -B -e -o clean install -DskipTests=true

# Estágio 2: Usa uma imagem JRE mínima para executar o aplicativo
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o arquivo JAR gerado do primeiro estágio
COPY --from=builder /build/entrypoints/target/entrypoints-1.0-SNAPSHOT.jar .

# O comando a ser executado quando o contêiner iniciar
CMD ["java", "-jar", "entrypoints-1.0-SNAPSHOT.jar"]