package br.com.digitalhouse.dhwallet.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      installSplashScreen() //Aqui instalamos a nossa Splash Screnn
        setContent {
           Navigator() //Chamando nosso Navegator que tem as telas do nosso App
        }
    }
}


