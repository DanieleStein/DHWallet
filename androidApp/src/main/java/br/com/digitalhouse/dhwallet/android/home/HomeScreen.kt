package br.com.digitalhouse.dhwallet.android.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.R
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar
import br.com.digitalhouse.dhwallet.android.component.DHCardGroup
import br.com.digitalhouse.dhwallet.android.component.Transacao
import br.com.digitalhouse.dhwallet.model.Transaction
import br.com.digitalhouse.dhwallet.util.DataResult
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(onProfileNavigation: () -> Unit, onItemDetail: (Int) -> Unit) {//onBack(uma funcao que vai exucutar ()um bloco -> retorno Unit(Void)/onItemDetail(aqui na home vai jogar para o navigation)

  val viewModel = viewModel<HomeViewModel>()
  val transactions by viewModel.transactions.collectAsState()
  val profile by viewModel.profile.collectAsState()

  MyApplicationTheme {
    Scaffold( //Serve como Surface, porém com mais funcionalidades, como o TopBar que usaremos
      topBar = { CenterTopBar(
        title = "DH Wallet",
        onProfileNavigation = onProfileNavigation) },//chamando nosso Componente CenterTopBar
    ) { _ ->
      when (transactions) { //quando recebermos Transactions
        is DataResult.Loading -> LoadingIndicator() //se for do tipo dataResult.Loading vai mostrar o LoadingIndicator
        is DataResult.Error -> ErrorMessage((transactions as DataResult.Error).error) //se for do tipo dataResult.Error vai mostrar o Transactions.error
        is DataResult.Sucess ->  ContentHome(transactions as DataResult.Sucess<List<Transaction>>, onItemDetail) //se for do tipo dataResult.Sucess vai mostrar a ContentHome com a lista de Transactions/onItemetail vai jogar para o item de cima, que é nos paramentros
        else -> Unit //nenhum dos casos acima, vamos dar um Unit
      }
    }
  }
}

@Composable
fun ErrorMessage(error: Throwable) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(text ="Ops! Deu erro ${error.message}") //Vou pegar a mensagem que vier na nossa requisição($error.message)
  }
}


@Composable
fun LoadingIndicator() {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
    ) {
    CircularProgressIndicator()
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentHome(resultado : DataResult.Sucess<List<Transaction>>, onItemDetail: (Int) -> Unit) { //aqui no content
  val transactions = resultado.data

  LazyColumn() {

    item {
      DHCardGroup()//Chamando nosso cartão
    }

    item {
      Row(
        modifier = Modifier.padding(20.dp),//Espaço entre as bordas do Texto
        verticalAlignment = Alignment.CenterVertically //alinhados na vertical
      ) {
        Text(text = "Transações", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f)) //espaço entre a escrita e a seta
        IconButton(onClick = { onItemDetail.invoke(1) }) { //chamada de dentro pra fora, vai invokar e vai jogar para o item de cima(DataResultSucess(ContentHome))
          Icon(Icons.Filled.ArrowForward, "backIcon", tint = Color.Black)
        }
      }
    }

    val grouping = transactions.groupBy { it.date } //Vai agrupar todos que tenham a mesma data(rola a data a té a proxima data e após ficxa a segunda enquanto rola os itens

    grouping.forEach { (date, trans) ->
      stickyHeader { 
        Text(text = "${date.dayOfMonth}/${date.month}", modifier = Modifier.fillMaxWidth().background(Color.Gray))
      }

      items(trans.size) {
        val painter = rememberAsyncImagePainter(
          model =
          ImageRequest.Builder(LocalContext.current)
            .data(transactions[it].logo + "?q=$it")//Trazendo o logo da api, e colocando um final nele para ele trazer imagens aleatorias("?q=$it")
            .size(50)
            .placeholder(R.drawable.shape)
            .build()
        )
        Transacao(
          image = {
            Image(
              painter = painter,
              contentDescription = "Profile Image",
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .height(56.dp)//altura
                .width(56.dp)//largura
                .clip(CircleShape)//ajusta a imagem em circulo
                .background(Color(0x1CFD3C72))//cor fundo da imagem com opacidade
                .padding(10.dp)//na imagem tem o padding, vai reduzir a nossa imagem
                .clip(CircleShape)//e vai trazer a umagem em um circulo
            )
          },
          title = transactions[it].title, //Trazendo os valores que estão na nossa lista em LoadTransaction
          subtitle = transactions[it].transactionType.description, //Trazendo os valores que estão na nossa lista em LoadTransaction
          value = {
            Text(text = "R$ ${transactions[it].value}", color = Color.Green, fontWeight = FontWeight.Bold, fontSize = 20.sp)//Tranzendo os valores que estão na nossa lista em LoadTransaction
          },
        )
      }
    }
  }
}


@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen(onItemDetail = {}, onProfileNavigation = {})
}
