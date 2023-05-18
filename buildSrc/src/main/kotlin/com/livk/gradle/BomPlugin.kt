package com.livk.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlatformExtension
import org.gradle.api.plugins.JavaPlatformPlugin

/**
 * <p>
 * BomPlugin
 * </p>
 *
 * @author livk
 */
class BomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaPlatformPlugin::class.java)

        project.extensions.getByType(JavaPlatformExtension::class.java).allowDependencies()
    }
}
