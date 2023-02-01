package br.com.digitalhouse.dhwallet.android.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.R
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun Transacao(
  image: @Composable () -> Unit,//Onde é a coluna de image,recebo um composable
  title: String, //titulo é o nome da logo
  subtitle: String, //subtitulo, credito ou debito
  value: @Composable () -> Unit = {}, //valor da transacao
  onDetailNavigate: () -> Unit = {} //botao para navegar
) {
  Card( //ele é em formato de card
    modifier = Modifier.padding(bottom = 0.5.dp)//padding(Bottom é que ele tem uma linha entre as transações
  ) {
    Row( //itens na vertical
      verticalAlignment = Alignment.CenterVertically,//alinhados na vertical
      modifier = Modifier
        .fillMaxWidth() //toda a tela
        .padding(20.dp) //espaco das bordas do cartao
        .clickable { onDetailNavigate.invoke() }
    ) {
      image()
      Spacer(modifier = Modifier.width(20.dp))//espaco entre imagem e texto da coluna abaixo
      Column {//coluna
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 17.sp)//nosso titulo da logo
        Text(text = subtitle, fontSize = 13.sp, color = Color.Gray) //subtitulo com o credito ou pagamento
      }
      Spacer(modifier = Modifier.weight(1f))//espaco entre o texto e o valor
      value()
    }
  }
}


@Preview
@Composable
fun Transacao_Preview() {
  val painter = rememberAsyncImagePainter(model =
  ImageRequest.Builder(LocalContext.current)
    .data(R.drawable.shape)
    .size(58)
    .placeholder(R.drawable.shape)
    .build()
  )
  Transacao(
    image = {
      Image(
        painter = painter,
        contentDescription = "Profile Image",
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .height(56.dp)//altura
          .width(56.dp)//largura
          .clip(CircleShape)//ajusta a imagem em circulo
          .background(Color(0x1CFD3C72))//cor fundo da imagem com opacidade
          .padding(10.dp)//na imagem tem o padding, vai reduzir a nossa imagem
          .clip(CircleShape)//e vai trazer a umagem em um circulo
      )
    },  
    title = "Drible Inc",
    subtitle = "Crédito",
    value = {
      Text(text = "+ R$ 45,00", color = Color.Green, fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
  )
}
