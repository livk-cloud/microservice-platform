package com.livk.gradle.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension

/**
 * <p>
 * OptionalPlugin
 * </p>
 *
 * @author livk
 */
abstract class OptionalPlugin : Plugin<Project> {
    companion object {
        const val OPTIONAL = "optional"
    }

    override fun apply(project: Project) {
        val configurations = project.configurations
        project.pluginManager.apply(JavaPlugin::class.java)
        configurations.create(OPTIONAL) { optional ->
            optional.isCanBeResolved = false
            optional.isCanBeConsumed = false
            project.plugins.withType(JavaPlugin::class.java) {
                project.extensions.getByType(JavaPluginExtension::class.java).sourceSets.all {
                    configurations.getByName(it.compileClasspathConfigurationName).extendsFrom(optional)
                    configurations.getByName(it.runtimeClasspathConfigurationName).extendsFrom(optional)
                }
            }
        }
    }
}
