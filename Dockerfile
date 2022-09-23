# 통합 설정
#FROM azul/zulu-openjdk:8
#EXPOSE 8080
#ADD ./build/libs/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#Maven 설정
#FROM azul/zulu-openjdk:8
##CMD ["./mvnw", "clean", "package"]
##ARG JAR_FILE_PATH=target/*.jar
#CMD ["./gradlew", "clean", "build"]
#ARG JAR_FILE_PATH=build/libs/*.jar
#COPY ${JAR_FILE_PATH} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]

#Gradle 설정
FROM azul/zulu-openjdk:8
CMD ["./gradlew", "clean", "build"]
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]