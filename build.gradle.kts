plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.2.2").apply(false)
    id("com.android.library").version("7.2.2").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    id("org.jetbrains.kotlin.plugin.serialization").version("1.7.10").apply(false)//lib de serialization(se na api estiver 1.8.0) aqui usamos a 1.7.10
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
