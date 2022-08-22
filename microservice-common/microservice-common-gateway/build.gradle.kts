description = "microservice "

dependencies {
    api(project(":microservice-common:microservice-common-redis"))
    optional("org.springframework.cloud:spring-cloud-gateway-server")
}
