package br.com.digitalhouse.dhwallet.android.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar
import br.com.digitalhouse.dhwallet.android.component.TopBar

@Composable
fun HomeScreen(onBack: () -> Unit) {//onBack(uma funcao que vai exucutar ()um bloco -> retorno Unit(Void)
    MyApplicationTheme {
        Scaffold( //Serve como Surface, porém com mais funcionalidades, como o TopBar que usaremos
            topBar = { CenterTopBar(title = "DH Wallet") {}//chamando nosso Componente CenterTopBar
            }
        ) {
            Column(modifier = Modifier.padding(it)) {//Quando usamos Scaffold, em algumas funções ele precisa medir todos os itens da tela, e assim o botao não corta nosso conteudo
                Text(text = "Home")
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen() {}
}