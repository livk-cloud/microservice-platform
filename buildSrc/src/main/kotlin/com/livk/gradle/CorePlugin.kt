package com.livk.gradle

import com.livk.gradle.dependency.CompileProcessorPlugin
import com.livk.gradle.dependency.ManagementPlugin
import com.livk.gradle.dependency.OptionalPlugin
import com.livk.gradle.info.ManifestPlugin
import com.livk.gradle.tasks.DeleteExpand
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * <p>
 * CorePlugin
 * </p>
 *
 * @author livk
 */
class CorePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(DeleteExpand::class.java)
        project.pluginManager.apply(ManagementPlugin::class.java)
        project.pluginManager.apply(OptionalPlugin::class.java)
        project.pluginManager.apply(CompileProcessorPlugin::class.java)
        project.pluginManager.apply(ManifestPlugin::class.java)
    }

}
