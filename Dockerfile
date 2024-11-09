# 使用基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 将 JAR 文件复制到容器中
COPY target/my-api.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

# 指定容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]

# 暴露应用监听的端口（如8080）
EXPOSE 8080