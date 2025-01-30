# Используем официальный образ Maven для сборки приложения
FROM maven:3.8.4-openjdk-17 AS build

# Устанавливаем рабочую директорию
WORKDIR /bookService

# Копируем файл pom.xml и загружаем зависимости
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Используем официальный образ OpenJDK для запуска приложения
FROM openjdk:17-jre-slim

# Устанавливаем рабочую директорию
WORKDIR /bookService

# Копируем скомпилированный JAR файл из образа сборки
COPY --from=build /bookService/target/*.jar bookService.jar

# Открываем порт, на котором будет работать приложение
EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "bookService.jar"]
