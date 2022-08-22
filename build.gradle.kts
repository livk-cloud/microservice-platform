plugins {
    id("com.livk.root")
}

val bom = setOf(project(":microservice-dependencies"))
val gradleModuleProjects = subprojects.filter {
    it.buildFile.exists()
}.toSet() - bom
val commonModuleProjects = gradleModuleProjects.filter {
    it.name.startsWith("microservice-common-") || it.name.endsWith("-api")
}.toSet()
val springModuleProjects = gradleModuleProjects - commonModuleProjects

configure(bom) {
    apply(plugin = "com.livk.bom")
}

configure(commonModuleProjects) {
    apply(plugin = "com.livk.common")
}

configure(springModuleProjects) {
    apply(plugin = "com.livk.service")

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
    }
}

configure(gradleModuleProjects) {
    apply(plugin = "com.livk.module")

    dependencies {
        management(platform(project(":microservice-dependencies")))
        provider("org.projectlombok:lombok")
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
