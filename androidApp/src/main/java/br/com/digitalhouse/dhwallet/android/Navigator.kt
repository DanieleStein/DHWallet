package br.com.digitalhouse.dhwallet.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.digitalhouse.dhwallet.android.home.HomeScreen
import br.com.digitalhouse.dhwallet.android.login.LoginScreen

enum class Route {
    LOGIN, HOME
}
@Composable
fun Navigator(  //Telas do nosso app que serão navegadas
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    initial: Route = Route.LOGIN
) {
    NavHost(
        navController = navHostController,
        startDestination = initial.name ) {

        composable(Route.LOGIN.name) {
            LoginScreen {
                navHostController.navigate(Route.HOME.name)
            }
        }
        composable(Route.HOME.name) {
            HomeScreen {
                navHostController.popBackStack()//Desta forma ele não deixara as telas empilhadas, quando voltar a tela vai sumir
            }
        }
    }
}