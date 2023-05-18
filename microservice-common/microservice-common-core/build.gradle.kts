description = "microservice通用核心包"

dependencies {
    optional("org.springframework:spring-webflux")
    optional("io.projectreactor.netty:reactor-netty-http")
    optional("org.springframework.boot:spring-boot-starter")
    api("io.github.livk-cloud:spring-extension-commons")
    compileProcessor("io.github.livk-cloud:spring-auto-service")
}
