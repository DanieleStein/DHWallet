package br.com.digitalhouse.dhwallet.extension

import br.com.digitalhouse.dhwallet.util.DataResult
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

fun <T : Any> Flow<DataResult<T>>.updateState() =
  retryWhen { cause, attempt ->
    if(cause is IOException && attempt < 3) { //RetryWhen(Usuario ficou sem internet, e tentativas menor que 3
      delay(5000) //esperar 5 segundos, e tenta novem
      true
    } else {
      false
    }
  }
    .onStart { emit(DataResult.Loading(isLoading = true)) } //Quando iniciar o DataResult será de Loading é true
    .catch { emit(DataResult.Error(it)) } //Se der um erro da API, vai mostrar o Data Result de erro
    //.onCompletion { emit(DataResult.Loading(isLoading = false)) } //Exibe estado de Loading novamente mas escondido(App vai dar sucesso, passa pelo loading, exibe os dados, ele termina e esconde loading
