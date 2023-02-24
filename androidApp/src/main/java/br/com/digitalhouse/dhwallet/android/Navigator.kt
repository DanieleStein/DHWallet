package br.com.digitalhouse.dhwallet.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.digitalhouse.dhwallet.android.home.HomeScreen
import br.com.digitalhouse.dhwallet.android.login.LoginScreen
import br.com.digitalhouse.dhwallet.android.transactions.TransactionScreen

enum class Route {
    LOGIN, HOME, TRANSACTIONS
}
@Composable
fun Navigator(  //Telas do nosso app que serão navegadas
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    initial: Route = Route.HOME
) {
    NavHost(
        navController = navHostController,
        startDestination = initial.name
    ) {
      composable(Route.LOGIN.name) {
        LoginScreen {
          navHostController.navigate(Route.HOME.name)
        }
      }
      composable(Route.HOME.name) {
        HomeScreen(
          onProfileNavigation = { navHostController.popBackStack() }, //Desta forma ele não deixara as telas empilhadas, quando voltar para a tela anterior vai sumir a tela atual
          onItemDetail = { params -> //aquele item em parenteses é o que estou passando aqui como paramentro(id da transação)
            navHostController.navigate("${Route.TRANSACTIONS}/$params") }
        )
      }
      composable("${Route.TRANSACTIONS}/{id}") {
        val id = it.arguments?.getString("id")

        TransactionScreen(id ?: "0")

      }
    }
}

