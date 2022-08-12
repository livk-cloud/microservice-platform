package com.livk.gradle

import com.livk.gradle.ModulePlugin
import com.livk.gradle.compile.ResourcesPlugin
import com.livk.gradle.info.ManifestPlugin
import com.livk.gradle.maven.DeployedPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin

/**
 * <p>
 * CommonPlugin
 * </p>
 *
 * @author livk
 * @date 2022/8/10
 */
class CommonPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(JavaLibraryPlugin::class.java)
        project.pluginManager.apply(ModulePlugin::class.java)
        project.pluginManager.apply(ResourcesPlugin::class.java)
        project.pluginManager.apply(ManifestPlugin::class.java)
        project.pluginManager.apply(DeployedPlugin::class.java)
    }
}
