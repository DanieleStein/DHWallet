package br.com.digitalhouse.dhwallet.android.transactions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionScreen(id: String) {
  MyApplicationTheme {
    Scaffold(
      topBar = {
        CenterTopBar(title = "DH Wallet", onProfileNavigation = {}
        )
      }
    ) { _ ->
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = "Meu ID $id", fontSize = 25.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}

@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(id = "7")
}
