package com.livk.gradle.tasks

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.language.base.plugins.LifecycleBasePlugin

/**
 * <p>
 * DeleteExt
 * </p>
 *
 * @author livk
 */
abstract class DeleteExpand : Plugin<Project> {
    companion object {
        private const val CLEAN_ALL_TASK_NAME = "cleanAll"
    }

    override fun apply(project: Project) {
        project.tasks.withType(Delete::class.java) {
            it.delete(project.projectDir.absolutePath + "/build")
            it.delete(project.projectDir.absolutePath + "/out")
            it.delete(project.projectDir.absolutePath + "/bin")
        }

        project.tasks.register(CLEAN_ALL_TASK_NAME, Delete::class.java) {
            it.group = LifecycleBasePlugin.BUILD_GROUP
            it.delete(project.projectDir.absolutePath + "/src/main/generated")
            it.delete(project.projectDir.absolutePath + "/src/test/generated_tests")
        }
    }
}
