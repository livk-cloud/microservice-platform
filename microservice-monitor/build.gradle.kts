description = "microservice监控"

dependencies {
    implementation(project(":microservice-common:microservice-common-core"))
    implementation(project(":microservice-common:microservice-common-redis"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("de.codecentric:spring-boot-admin-starter-server")
}
