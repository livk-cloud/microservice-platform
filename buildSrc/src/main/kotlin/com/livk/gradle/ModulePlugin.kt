package com.livk.gradle

import com.livk.gradle.compile.CompileArgsPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * <p>
 * ModulePlugin
 * </p>
 *
 * @author livk
 */
class ModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.pluginManager.apply(CompileArgsPlugin::class.java)
        project.pluginManager.apply(CorePlugin::class.java)
    }
}
