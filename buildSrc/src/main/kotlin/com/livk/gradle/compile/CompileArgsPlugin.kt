package com.livk.gradle.compile

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test

/**
 * <p>
 * CompileArgsPlugin
 * </p>
 *
 * @author livk
 */
abstract class CompileArgsPlugin : Plugin<Project> {
    companion object {
        val COMPILER_ARGS = ArrayList<String>()
        val MAPSTRUCT_COMPILER_ARGS = ArrayList<String>()
        const val MAPSTRUCT_PROCESSOR_NAME = "mapstruct-processor"
        const val UTF_8 = "UTF-8"

        init {
            COMPILER_ARGS.addAll(
                listOf(
                    "-Xlint:-options",
                    "-Xlint:rawtypes",
                    "-Xlint:deprecation",
                    "-Xlint:unchecked"
                )
            )
            MAPSTRUCT_COMPILER_ARGS.addAll(listOf("-Amapstruct.unmappedTargetPolicy=IGNORE"))
        }
    }

    override fun apply(project: Project) {
        project.pluginManager.apply(JavaPlugin::class.java)
        val javaCompile = project.tasks.getByName(JavaPlugin.COMPILE_JAVA_TASK_NAME) as JavaCompile

        addCompile(javaCompile)

        val test = project.tasks.getByName(JavaPlugin.COMPILE_TEST_JAVA_TASK_NAME)
        val javaTestCompile = test as JavaCompile
        addCompile(javaTestCompile)

        project.tasks.withType(Test::class.java) {
            it.useJUnitPlatform()
        }

        project.afterEvaluate {
            val dependencyName = HashSet<String>()
            project.configurations.forEach {
                dependencyName.addAll(it.dependencies.map { dependency -> dependency.name })
            }
            if (dependencyName.contains(MAPSTRUCT_PROCESSOR_NAME)) {
                javaCompile.options.compilerArgs.addAll(MAPSTRUCT_COMPILER_ARGS)
            }
        }
    }

    private fun addCompile(javaCompile: JavaCompile) {
        javaCompile.options.compilerArgs.addAll(COMPILER_ARGS)
        javaCompile.options.encoding = UTF_8
        javaCompile.sourceCompatibility = JavaVersion.VERSION_17.toString()
        javaCompile.targetCompatibility = JavaVersion.VERSION_17.toString()
    }
}
