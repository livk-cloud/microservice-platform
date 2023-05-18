package com.livk.gradle.compile

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.plugins.JavaPlugin

/**
 * <p>
 * ResourcesPlugin
 * </p>
 *
 * @author livk
 */
abstract class ResourcesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.tasks
            .getByName(JavaPlugin.COMPILE_JAVA_TASK_NAME)
            .dependsOn(JavaPlugin.PROCESS_RESOURCES_TASK_NAME)
    }
}
