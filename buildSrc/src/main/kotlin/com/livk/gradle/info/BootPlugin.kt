package com.livk.gradle.info

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.dsl.SpringBootExtension
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

/**
 * <p>
 * BootPlugin
 * </p>
 *
 * @author livk
 */
abstract class BootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaPlugin::class.java)
        project.pluginManager.apply(SpringBootPlugin::class.java)
        project.extensions
            .getByType(SpringBootExtension::class.java)
            .buildInfo {
                it.properties { build ->
                    build.group.set(project.group.toString())
                    build.version.set(project.version.toString())
                    build.time.set(DateTimeFormatter.ISO_INSTANT.format(Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8))))
                }
            }
        val bootJar = project.tasks.getByName(SpringBootPlugin.BOOT_JAR_TASK_NAME) as BootJar
        bootJar.archiveBaseName.set(project.name)
        bootJar.archiveFileName.set(bootJar.archiveBaseName.get() + "." + bootJar.archiveExtension.get())
        bootJar.launchScript()
        (project.tasks.getByName(JavaPlugin.JAR_TASK_NAME) as Jar).enabled = false
    }
}
