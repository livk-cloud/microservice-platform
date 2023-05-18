package com.livk.gradle

import com.livk.gradle.compile.ResourcesPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin

/**
 * <p>
 * CommonPlugin
 * </p>
 *
 * @author livk
 */
class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.pluginManager.apply(ModulePlugin::class.java)
        project.pluginManager.apply(ResourcesPlugin::class.java)
    }
}
