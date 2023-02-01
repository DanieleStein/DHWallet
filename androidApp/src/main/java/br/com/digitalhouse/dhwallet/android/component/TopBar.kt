package br.com.digitalhouse.dhwallet.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.R

@OptIn(ExperimentalMaterial3Api::class)//Usada anotacao, pois o CenterSAliggnedTopBar esta em faze experimental
@Composable
fun CenterTopBar(title: String, onProfileNavigation: () -> Unit) { //onProfileNavigation do tipo ação() e sem retorno ->Unit
    CenterAlignedTopAppBar( //Para o nosso texto ficar centradalizado na TopBar
        title = { Text(title, fontWeight = FontWeight.Bold, fontSize = 24.sp) }, //Alterações de texto na topBar
        actions = {
            IconButton(onClick = onProfileNavigation) { //Criado o botão do Profile que ficara sempre na topBar
                Image( //Vai ser uma imagem
                    painter = painterResource(R.drawable.photo), //R(buusca na nossa pasta res)depois drawable, depois nome da nossa magem
                    contentDescription = "Profile", //descricao da imagem(nome)
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(40.dp)
                        .clip(CircleShape)
                )
            }
        }
    )
}

@Composable
fun TopBar(title: String, onBack: () -> Unit, onProfileNavigation: () -> Unit) { //onBack(ação de voltar)
    TopAppBar( //Temos outra TopBar no App que é setinha,titulo e foto
        title = { Text(text = title, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = Color.White) }, //Vai ser o titulo da topBar
        navigationIcon = {
            IconButton(onClick = onBack) { //Botao de voltar(OnBack)( ação de voltar)
                Icon(Icons.Filled.ArrowBack, "backIcon", tint = Color.White) //Botao de setinha de voltar(<-)
            }
        },
        actions = {
            IconButton(onClick = onProfileNavigation) { //Criado o botão do Profile que ficara sempre na topBar
                Image( //Vai ser uma imagem
                    painter = painterResource(R.drawable.ic_baseline_account_circle_24), //R(buusca na nossa pasta res)depois drawable, depois nome da nossa magem
                    contentDescription = "Profile", //descricao da imagem(nome)
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(40.dp)
                        .clip(CircleShape)
                )
            }
        },
        //contentColor = Color.White, //E a cor da letra branca(não funcionou)
        backgroundColor = Color.Transparent //Nossa TopBar vai ficar Transparente
    )
}

@Composable
@Preview
fun CenterTopBar_Preview() {
    CenterTopBar(title = "DH Wallet") {
    }
}

@Composable
@Preview
fun TopBar_Preview() {
    TopBar(title = "Olá souTopBar", onBack = { /*TODO*/ }) {

    }
}
