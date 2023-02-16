package br.com.digitalhouse.dhwallet.android.home

import android.annotation.SuppressLint
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
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.R
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar
import br.com.digitalhouse.dhwallet.android.component.DHCardGroup
import br.com.digitalhouse.dhwallet.android.component.TopBar
import br.com.digitalhouse.dhwallet.android.component.Transacao
import br.com.digitalhouse.dhwallet.model.Profile
import br.com.digitalhouse.dhwallet.model.Transaction
import br.com.digitalhouse.dhwallet.network.Network
import br.com.digitalhouse.dhwallet.network.Network.loadTransaction
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(onBack: () -> Unit) {//onBack(uma funcao que vai exucutar ()um bloco -> retorno Unit(Void)

  val viewModel = viewModel<HomeViewModel>()
  val transactions by viewModel.transactions.collectAsState()
  val profile by viewModel.profile.collectAsState()

  MyApplicationTheme {
    Scaffold( //Serve como Surface, porém com mais funcionalidades, como o TopBar que usaremos
      topBar = { CenterTopBar(title = "DH Wallet") {} },//chamando nosso Componente CenterTopBar
    ) { _ ->
      if (transactions.isEmpty()) {
        LoadingIndicator()
      } else {
        ContentHome(transactions)
      }
    }
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

@Composable
fun ContentHome(transactions: List<Transaction>) {
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
        IconButton(onClick = { /*TODO*/ }) {
          Icon(Icons.Filled.ArrowForward, "backIcon", tint = Color.Black)
        }
      }
    }


    items (transactions.size) {//transaction.size(vai replicar a lista de transactiona a quantidade de vezes(size) que foi colocado la no loadTransaction)
      val painter = rememberAsyncImagePainter(
        model =
        ImageRequest.Builder(LocalContext.current)
          .data(transactions[it].logo + "?q=$it")//Trazendo os valores que estão na nossa lista em LoadTransaction
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

@Preview
@Composable
fun HomeScreen_Preview() {
    HomeScreen() {}
}
