description = "microservice通用核心包"

dependencies {
    optional("org.springframework:spring-webflux")
    optional("io.projectreactor.netty:reactor-netty-http")
    optional("org.springframework.boot:spring-boot-starter")
    optional("com.fasterxml.jackson.core:jackson-databind")
    optional("jakarta.servlet:jakarta.servlet-api")
    api("org.apache.commons:commons-lang3")
    api("com.google.code.findbugs:annotations")
    api("com.google.guava:guava")
}
