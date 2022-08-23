description = "microservice网关"

dependencies {
    implementation(project(":microservice-common:microservice-common-core"))
    implementation("de.codecentric:spring-boot-admin-starter-client")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
}
