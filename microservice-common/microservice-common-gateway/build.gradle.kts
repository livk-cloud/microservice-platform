description = "microservice "

dependencies {
    api(project(":microservice-common:microservice-common-redis"))
    api("com.github.ben-manes.caffeine:caffeine")
    optional("org.springframework.cloud:spring-cloud-starter-gateway")
}
