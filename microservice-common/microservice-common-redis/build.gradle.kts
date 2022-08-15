description = "microservice redis包"

dependencies {
    optional("jakarta.servlet:jakarta.servlet-api")
    api(project(":microservice-common:microservice-common-core"))
    api("org.springframework.boot:spring-boot-starter-aop")
    api("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    api("org.redisson:redisson-spring-boot-starter")
}
