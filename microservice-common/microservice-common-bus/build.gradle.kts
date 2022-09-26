description = "microservice消息总线"

dependencies {
    api(project(":microservice-common:microservice-common-core"))
    api("org.springframework.cloud:spring-cloud-bus")
    api("org.springframework.boot:spring-boot-starter-aop")
}
