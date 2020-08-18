#依赖jdk8
FROM java:8
#维护者信息
MAINTAINER duchong 1427222829@qq.com
#容器卷
VOLUME /tmp
#拷贝jar包
COPY target/docker-demo-0.0.1-SNAPSHOT.jar /docker-demo.jar
#暴漏端口
EXPOSE 8080
#容器启动时执行
ENTRYPOINT [ "java", "-jar", "/docker-demo.jar" ]