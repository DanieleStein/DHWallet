package br.com.digitalhouse.dhwallet.android.transactions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.component.CenterTopBar
import br.com.digitalhouse.dhwallet.model.Transaction
import br.com.digitalhouse.dhwallet.model.TransactionType
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import kotlinx.datetime.LocalDate

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

        Spacer(modifier = Modifier.height(30.dp))

        val transactions = MutableList(3) {
          Transaction(
            it,
            "",
            "Título ${it+1}",
            "",
            TransactionType.CREDIT,
            (it + 10).toDouble(),
            LocalDate.parse("2023-01-01")
          )
        }

        val slices = transactions.map {  //Nosso gráfico agora puxando as nossas transações.
          PieChartData.Slice(it.value.toFloat(), Color.Cyan)
        }

        //val slices = listOf( //Nosso gráfico
        //  PieChartData.Slice(10f, Color.Blue),
        //  PieChartData.Slice(30f, Color.Green),
        //  PieChartData.Slice(40f, Color.Magenta),
        //  PieChartData.Slice(20f, Color.Cyan)
        //)

        Row( //GRAFICO DE PIZZA
          verticalAlignment = Alignment.CenterVertically
        ) {
          PieChart(
            pieChartData = PieChartData(slices),
            modifier = Modifier
              .height(150.dp) //altura do gráfico
              .weight(0.5f)   //0.5, pois quero deixar o grafico na metade da linha
              .wrapContentWidth(),
            sliceDrawer = SimpleSliceDrawer(sliceThickness = 100f) //Aqui ele vai preencher todo o gráfico(100%)
          )
          Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
          ) {
            transactions.forEach{//legenda puxando os dados da transações
              LabelItem(color = Color.Cyan, name = "${it.title} (${it.value.toInt()}%)")
            //slices.forEach {//Legenda do gráfico
            //  LabelItem(color = it.color, name = "Abc(${it.value.toInt()}%)")
            }

          }
        }

        BarChart( //GRAFICO COLUNAS
          barChartData = BarChartData(
            bars = listOf(
              BarChartData.Bar(
                label = "Bar Label",
                value = 100f,
                color = Color.Cyan
              ),
              BarChartData.Bar(
                label = "Bar Label",
                value = 40f,
                color = Color.Yellow
              ),
              BarChartData.Bar(
                label = "Bar Label",
                value = 10f,
                color = Color.Green
              )
            )
          ),
          modifier = Modifier
            .fillMaxHeight(0.5f),
          animation = simpleChartAnimation(),
          barDrawer = SimpleBarDrawer(),
          xAxisDrawer = SimpleXAxisDrawer(),
          yAxisDrawer = SimpleYAxisDrawer(),
          labelDrawer = SimpleValueDrawer(drawLocation = SimpleValueDrawer.DrawLocation.Outside) //valores dos titulos, abaixo, acima, ou dentro da coluna
        )

        /*LineChart(
          linesChartData = listOf(
            LineChartData(
              points = listOf(
                LineChartData.Point (
                  1f,
                  "Label 1"
                ),
                LineChartData.Point (
                  10f,
                  "Label 2"
                ),
                LineChartData.Point (
                  40f,
                  "Label 3"
                )
              ),
              lineDrawer = SolidLineDrawer()
            )
          ),
          modifier = Modifier.fillMaxSize(),
          animation = simpleChartAnimation(),
          pointDrawer = FilledCircularPointDrawer(),
          xAxisDrawer = SimpleXAxisDrawer(),
          yAxisDrawer = SimpleYAxisDrawer(),
          horizontalOffset = 5f,
          labels = listOf("label 1", "label 2", "label3")
        )*/// GRÁFICO LINHA CRESCENTE
      }
    }
  }
}

@Composable
fun LabelItem(color: Color, name: String, nameColor: Color = Color.Gray) {
  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(Icons.Filled.Circle, contentDescription = name, modifier = Modifier.height(10.dp), tint = color)
    Spacer(modifier = Modifier.width(16.dp))
    Text(text = name, color = nameColor)
  }

}

@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(id = "7")
}
