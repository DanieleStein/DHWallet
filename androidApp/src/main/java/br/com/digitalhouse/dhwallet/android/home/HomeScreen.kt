package br.com.digitalhouse.dhwallet.android.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.R
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar
import br.com.digitalhouse.dhwallet.android.component.DHCardGroup
import br.com.digitalhouse.dhwallet.android.component.TopBar
import br.com.digitalhouse.dhwallet.android.component.Transacao
import br.com.digitalhouse.dhwallet.api.Api
import br.com.digitalhouse.dhwallet.network.Network
import br.com.digitalhouse.dhwallet.network.Network.loadTransaction
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(onBack: () -> Unit) {//onBack(uma funcao que vai exucutar ()um bloco -> retorno Unit(Void)
    MyApplicationTheme {
        Scaffold( //Serve como Surface, porém com mais funcionalidades, como o TopBar que usaremos
            topBar = { CenterTopBar(title = "DH Wallet") {} }//chamando nosso Componente CenterTopBar
        ) {

          val scope = rememberCoroutineScope()
          val texto = remember { mutableStateOf("Transações") } //Texto de Loading(estado inicial)

          //Tudo que esta aqui dentro faz parte do coroutines
          LaunchedEffect(true) {//vai executar a primeira vez que o nosso componente for renderizado
            scope.launch { //Scopo(vai controlar o nosso servico, se sobe ou para, se esta na main ou nao) Launch(roda assincronamente)
              texto.value = try { //vai pegar o texto
                //Api.instance.login(Login("usuario@dhfood.com.br", "123456")),
                Api.instance.getAll().results.map { it.name }.toString() //vai informar que o novo valor agora é este,(personagens da api).map(pega um dado especifico da api)
              } catch (e: Exception) { //se der erro, vai aparecer a mensagem de erro
                e.message ?: "erro :("
              }
            }
          }

          //LazyColumn para ter a rolagem na tela inteira, separado por item
            LazyColumn(modifier = Modifier.padding(it)) {//Quando usamos Scaffold, em algumas funções ele precisa medir todos os itens da tela, e assim o botao não corta nosso conteudo

              item {
                  DHCardGroup()//Chamando nosso cartão
                  loadTransaction()
                }

              item {
                 Row(
                   modifier = Modifier.padding(20.dp),//Espaço entre as bordas do Texto
                   verticalAlignment = Alignment.CenterVertically //alinhados na vertical
                 ) {
                   Text(text = texto.value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                   Spacer(modifier = Modifier.weight(1f)) //espaço entre a escrita e a seta
                   IconButton(onClick = { /*TODO*/ }) {
                     Icon(Icons.Filled.ArrowForward, "backIcon", tint = Color.Black)
                   }
                 }
               }

              val transaction = loadTransaction() //Instanciando a nossa lista de transações

              items (transaction.size) {//size: 5(tamanho da nossa lista em loadTransactions)
                val painter = rememberAsyncImagePainter(model =
                ImageRequest.Builder(LocalContext.current)
                  .data(transaction[it].logo)//Trazendo os valores que estão na nossa lista em LoadTransaction
                  .size(50)
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
                  title = transaction[it].title, //Trazendo os valores que estão na nossa lista em LoadTransaction
                  subtitle = transaction[it].transactionType.description, //Trazendo os valores que estão na nossa lista em LoadTransaction
                  value = {
                    Text(text = "+ R$ ${transaction[it].value}", color = Color.Green, fontWeight = FontWeight.Bold, fontSize = 20.sp)//Tranzendo os valores que estão na nossa lista em LoadTransaction
                  }
                )
              }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen() {}
}
