# Используем официальный образ Maven для сборки приложения
FROM eclipse-temurin:17-jdk as build

WORKDIR /app

# Копируем необходимые файлы
COPY . .

# Приводим файл mvnw к нужному формату
RUN sed -i 's/\r$//' ./mvnw

# Делаем mvnw исполняемым
RUN chmod +x ./mvnw

# Собираем приложение
RUN ./mvnw clean package -DskipTests

# Проверяем наличие JAR файла
RUN ls -l /app/target

# Используем облегченный образ для выполнения приложения
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Копируем собранный JAR файл из предыдущего этапа
COPY --from=build /app/target/*.jar bookService.jar

EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "bookService.jar"]
