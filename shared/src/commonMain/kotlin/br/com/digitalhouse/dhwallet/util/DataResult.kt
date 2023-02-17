package br.com.digitalhouse.dhwallet.util

//Sealed class classe genérica
sealed class DataResult<out T : Any> { //out T:Any(vai receber qualquer tipo)
  data class Sucess<out T : Any>(val data: T) : DataResult<T>() //Vamos ter uma resposta de sucesso(sucesso do modelo de dados)
  data class Error(val error: Throwable) : DataResult<Nothing>() //Vamos ter um erro
  object Loading : DataResult<Nothing>() //Vamos ter um Loading Boolean(se vai exibir ou não)(vai estender de dataResult
  object Empty : DataResult<Nothing>() //Vai estender de DataResult, mas não vamos passar nada para ele, só saber se o tipo do DataResult vai ser um Empty
}
