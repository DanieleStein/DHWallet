package br.com.digitalhouse.dhwallet.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme

@Composable
fun HomeScreen() {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), //Toda a tela
            color = Color(0xFFBD22D8) //Cor de fundo da tela
        ) {
            Column(
                verticalArrangement = Arrangement.Center, //texto fica centralizado
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Sou a Home!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
            }
        }
    }

}

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen()
}