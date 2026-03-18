import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    api(libs.jimmer.core.kotlin)
    api(libs.jimmer.sql.kotlin)
    api(libs.quarkus.jimmer)

    ksp(libs.jimmer.ksp)
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions {
    freeCompilerArgs.set(listOf("-Xannotation-default-target=param-property"))
}