description = "microservice gateway"

dependencies {
    implementation(project(":microservice-common:microservice-common-core"))
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
}
