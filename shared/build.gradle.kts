plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }
    //sourceSets: Aqui nos vamos colocar a dependencia para o nosso KMM
    sourceSets {
        // commonMain: Vou utilizar as bibliotecas que funcionam tanto para android quanto para ios
        val commonMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        //androidMain: Usar uma lib expecifica para rodar só no ios
        val androidMain by getting {
            dependencies { //dessa forma adicionamos a dependencia só para android
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "br.com.digitalhouse.dhwallet"
    compileSdk = 32
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}