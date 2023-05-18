description = "microservice http interface"

dependencies {
    api(project(":microservice-common:microservice-common-core"))
    api("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}
