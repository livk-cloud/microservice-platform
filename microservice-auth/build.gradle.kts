description = "microservice授权中心"

dependencies {
    implementation(project(":microservice-common:microservice-common-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("de.codecentric:spring-boot-admin-starter-client")
}
