plugins {
    com.livk.bom
}

dependencies {
    api(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    api(platform(libs.spring.extension.dependencies))
    api(platform(libs.spring.cloud.dependencies))
    constraints {
        api(libs.mybatis.plus.starter)
    }
}
