package br.com.digitalhouse.dhwallet.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import br.com.digitalhouse.dhwallet.android.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@Composable
fun DHCardContent() { //Será as informações que estão no cartão
    val shadow = Shadow(Color.Black, offset = Offset(2f, 2f), blurRadius = 1f)//Sombra para nossos textos
    Column( //Deixa os itens um em cima do outro
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient( //Brush é usado para dar o efeito das duas cores no cartao
                    listOf( //lista de cores
                        Color(0xFF4A27F4),
                        Color(0xFF454BB2)
                    )
                )
            )
            .height(202.dp)//Altura do nosso cartão
            .padding(horizontal = 30.dp, vertical = 24.dp)//espaço das bordas(laterais e em cima) do cartao
            .fillMaxWidth()//toda a tela,
    ) {
        Row(//Deixa os itens um do lado do outro
            verticalAlignment = Alignment.CenterVertically) { //deixa eles(visa e limite) alinhados verticalmente
            Column {
                Text(text = "Limite", fontSize = 9.sp)
                Text(text = "R$18,47", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f)) //Vai abrir(espasar) até encostar em uma borda
            Image(painter = painterResource(id = R.drawable.ic_visa), contentDescription = "Visa")
        }
        Spacer(modifier = Modifier.weight(1f))//Espaçamento entre as linhas verticais
        Row() {
            MutableList(3) {//vai repetir três vezes nosso texto
                Text(text = "****", fontSize = 20.sp, style = TextStyle(shadow = shadow))//shadow(sombra no texto)
              Spacer(modifier = Modifier.weight(1f))
            }
            Text(text = "2309", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.weight(1f))//Espaçamento entre as linhas verticais
        Row() {
            Column() {
                Text(text = "NOME", fontSize = 9.sp)
                Text(text = "DANIELE STEIN", style = TextStyle(shadow = shadow))//shadow(sombra no texto)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column() {
                Text(text = "VALIDADE", fontSize = 9.sp)
                Text(text = "09/2030", style = TextStyle(shadow = shadow))//shadow(sombra no texto)
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DHCardGroup() { //Nosso carrosel de cartão, serão três cartões para visualizar
    HorizontalPager(
        count = 3,//Tres paginas de cartao
        contentPadding = PaddingValues(horizontal = 32.dp),//mostrando a beirada dos outros cartões, na tela do cartão atual
        modifier = Modifier.height(250.dp) //colocando o tamanho que o nosso cartao vai ocupar na tela, para ele não ocupar a tela toda
    ) { page ->
        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue//para cada pagina que estou rolando ele vai calcular e diminuir
        Card(//Fica com aparência de cartão
            contentColor = Color.White,//mudar a cor de texto(componente de texto do material.Text)
            modifier = Modifier.graphicsLayer { //espaço entre os cartões
             lerp(//Conforme os cartoes vao se movimentado, o cartao central vai aumentando e os laterais diminuindo
                 start = 0.90f,//começam com 0,90
                 stop = 1f,//e param com 1
                 fraction = 1f - pageOffset.coerceIn(0f, 1f)//pega as fracoes para calcular o inicio 0,90 e chegar até 1
             ).also {
                 scaleY = it//pegando a scala(tamanho) e passando os novos valores
                 scaleX = it
             }
            }
        ) {
            DHCardContent() //Chamada das informacões do cartão
        }
    }
}


@Preview
@Composable
fun DHCard_Preview() {
    DHCardContent()
}

@Preview
@Composable
fun DHCardGroup_Preview() {
    DHCardGroup()
}
