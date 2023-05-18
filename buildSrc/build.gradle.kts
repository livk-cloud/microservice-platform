import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-gradle-plugin")
    kotlin("jvm") version libs.versions.kotlin.get()
}

repositories {
    maven { setUrl("https://maven.aliyun.com/repository/public") }
    maven { setUrl("https://plugins.gradle.org/m2/") }
    maven { setUrl("https://repo.spring.io/release") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:${libs.versions.spring.boot.get()}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

gradlePlugin {
    plugins {
        create("bomPlugin") {
            id = "com.livk.bom"
            implementationClass = "com.livk.gradle.BomPlugin"
        }
        create("modulePlugin") {
            id = "com.livk.module"
            implementationClass = "com.livk.gradle.ModulePlugin"
        }
        create("commonPlugin") {
            id = "com.livk.common"
            implementationClass = "com.livk.gradle.CommonPlugin"
        }
        create("rootProjectPlugin") {
            id = "com.livk.root"
            implementationClass = "com.livk.gradle.RootProjectPlugin"
        }
        create("servicePlugin") {
            id = "com.livk.service"
            implementationClass = "com.livk.gradle.ServicePlugin"
        }
    }
}
