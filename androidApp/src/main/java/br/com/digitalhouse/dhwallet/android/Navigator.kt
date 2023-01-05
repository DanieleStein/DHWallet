package br.com.digitalhouse.dhwallet.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.digitalhouse.dhwallet.android.home.HomeScreen
import br.com.digitalhouse.dhwallet.android.login.LoginScreen

enum class Route { //enum class: lista fixa, usuário só pode escolher entre os itens que estão nesta lista
    LOGIN, HOME    //Vai ser para ignorar as letras maiusculas e minusculas de LOGIN, HOME
}
@Composable
fun Navigator(  //Telas do nosso app que serão navegadas
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    initial: Route = Route.LOGIN //Nossa tela inicial do App
) {
    NavHost(
        navController = navHostController,
        startDestination = initial.name ) {
        composable(Route.HOME.name) {
            HomeScreen() //Nossa tela de Home
        }
        composable(Route.LOGIN.name) {
            LoginScreen {  //LoginScreen(Nossa tela de Login)
                navHostController.navigate(Route.HOME.name)
            }//onHomeNavigate(Parametro da tela LoginScreen que vai chamar a tela de Home quando login e senha estiver ok)
        }

    }

}