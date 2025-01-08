# Usa una imagen base de Ubuntu minimalista
FROM ubuntu:22.04

# Instala dependencias necesarias
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    unzip \
    git \
    maven \
    && apt-get clean

# Instala JDK 23 manualmente
RUN wget -O /tmp/openjdk-23.0.1.tar.gz https://download.oracle.com/java/23/latest/jdk-23_linux-x64_bin.tar.gz && \
    mkdir -p /usr/lib/jvm && \
    tar -xzf /tmp/openjdk-23.0.1.tar.gz -C /usr/lib/jvm && \
    rm /tmp/openjdk-23.0.1.tar.gz

# Configura JAVA_HOME y PATH
ENV JAVA_HOME=/usr/lib/jvm/jdk-23.0.1
ENV PATH="$JAVA_HOME/bin:$PATH"

# Verifica la instalación de Java
RUN echo $JAVA_HOME && echo $PATH && java --version

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo pom.xml y las dependencias para instalar antes
COPY pom.xml /app/

# Descarga las dependencias
RUN mvn dependency:resolve

# Copia el código fuente del proyecto
COPY src /app/src

# Expone el puerto en el que Spring Boot corre
EXPOSE 8080

# Comando para ejecutar la aplicación en modo desarrollo
CMD ["mvn", "spring-boot:run"]
