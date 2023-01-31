package br.com.digitalhouse.dhwallet.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.R

@Composable
fun Transacao() {
  Column(
    modifier = Modifier
      .background(Color.White)
      .height(96.dp)
      .padding(horizontal = 30.dp, vertical = 24.dp)
      .fillMaxWidth()
  ) {
    Column() {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(
          modifier = Modifier
            .background(Color(0x1CFD3C72), shape = CircleShape)
            .clip(CircleShape)
            .size(50.dp)
        ) {
          Image(painter = painterResource(id = R.drawable.shape), contentDescription = "Shape",
            modifier = Modifier
              .size(27.5.dp)
              .clip(CircleShape)
          )
        }
        Spacer(modifier = Modifier.weight(0.25f))
        Column(
        ) {
          Text(text = "Dribble Inc.", fontSize = 17.sp, color = Color.Black, fontWeight = FontWeight.Bold)
          Text(text = "Crédito", fontSize = 13.sp, color = Color.Black)
        }
        Spacer(modifier = Modifier.weight(1f))
        Column() {
          Text(text = "+ R$ 45.0", fontSize = 20.sp, color = Color.Green, fontWeight = FontWeight.Bold)
        }
      }
    }
}
}


@Preview
@Composable
fun Transacao_Preview() {
  Transacao()
}
