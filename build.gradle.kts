plugins {
    com.livk.root
}

val bom = setOf(project(":microservice-dependencies"))
val gradleModuleProjects = subprojects.filter {
    it.buildFile.exists()
}.toSet() - bom
val commonModuleProjects = gradleModuleProjects.filter {
    it.name.startsWith("microservice-common-") || it.name.endsWith("-api")
}.toSet()
val springModuleProjects = gradleModuleProjects - commonModuleProjects

configure(commonModuleProjects) {
    apply(plugin = "com.livk.common")

    dependencies {
        compileProcessor("io.github.livk-cloud:spring-auto-service")
    }
}

configure(springModuleProjects) {
    apply(plugin = "com.livk.service")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
        implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
        optional("org.springframework.boot:spring-boot-devtools")
    }
}

configure(gradleModuleProjects) {
    apply(plugin = "com.livk.module")

    dependencies {
        management(platform(project(":microservice-dependencies")))
        compileProcessor("org.projectlombok:lombok")
        compileProcessor("org.springframework.boot:spring-boot-configuration-processor")
        compileProcessor("org.springframework.boot:spring-boot-autoconfigure-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework:spring-tx")
    }
}

configure(allprojects) {
    repositories {
        maven { setUrl("https://maven.aliyun.com/repository/public") }
        maven { setUrl("https://plugins.gradle.org/m2/") }
        maven { setUrl("https://repo.spring.io/release") }
    }
}
