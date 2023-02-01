plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") //plugin serialization
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
      val ktorVersion = "2.2.2"
        // commonMain: Vou utilizar as bibliotecas que funcionam tanto para android quanto para ios
        val commonMain by getting {
            dependencies {
              implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")//lib do coroutines
              implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")//lib de data para o Kmm
              implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")//lib serialization com Json, pois o serialization já foi adicionado lá em cima
              implementation("io.ktor:ktor-client-core:$ktorVersion")//lib cliente ktor
              implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")//lib de ponte do nosso app com o servidor , com retorno json
              implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")//lib serialization ktor, o encodeToString e decodeFromString é automágico(kkk)
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
              implementation("io.ktor:ktor-client-okhttp:$ktorVersion")//controle do http com o android
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
          dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")//controle do http com o ios
          }
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
