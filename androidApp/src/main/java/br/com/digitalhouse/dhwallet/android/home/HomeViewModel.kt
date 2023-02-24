package br.com.digitalhouse.dhwallet.android.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.dhwallet.api.Api
import br.com.digitalhouse.dhwallet.model.Login
import br.com.digitalhouse.dhwallet.model.Profile
import br.com.digitalhouse.dhwallet.model.Transaction
import br.com.digitalhouse.dhwallet.repository.TransactionRepository
import br.com.digitalhouse.dhwallet.util.DataResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//Aqui vamos receber o flow(ficar observando) e mandar para algum lugar
//Se inscreve que queremos aquele item por aqui(observa e pega os dados) e no nosso composable muda esses dados de estado

class HomeViewModel(
  private val repository: TransactionRepository = TransactionRepository.instance,
  //private val savedStateHandle: SavedStateHandle //Vamos usar para não perder(salva em disco) a pesquisa(valor que digitou) que o usuario esta fazendo, quando ele recebe um ligação
  //savedStateHandle(recupera o dado do disco, e joga novamente para a tela de usuário) se atela virar, ele não perde os dados informados, pois quando muda o esrtado ele recupera os dados

): ViewModel() { //Trazendo a instancia da nossa TransactionsRepository//Herdando de ViewModel

 //private val search: StateFlow<String> = savedStateHandle.getStateFlow("search_key", "") // search(lá na nossa screen vamos recuperar ele, assim como com o transaction)

  //fun inputSearch(query: String) { //função vai receber o que o usuario colocar de valor(pesquisa)(query: String)
  //  savedStateHandle["search_key"] = query //cada valor digitado, será salvo
  //}

  private val _transactions = MutableStateFlow<DataResult<List<Transaction>>>(DataResult.Empty) //emptyList(valor inicial sao de lista vazias)
  val transactions: StateFlow<DataResult<List<Transaction>>> = _transactions //Tenho um valor imutavel, e ninguem vai poder alterar esse valor, por isso colocamos a lista mutavel aqui dentro, para nao ser alterada

  private val _profile = MutableStateFlow<DataResult<Profile>?>(null)
  val profile: StateFlow<DataResult<Profile>?> = _profile

  init {
    login()//Quando nosso homeViewModel for chamada, vai inicar o login

  }
  fun login() = viewModelScope.launch {
    Api.token = Api.instance.login(Login("usuario@dhwallet.com.br", "123456")).token
  }.invokeOnCompletion {
    getTransactions()//Terminou o login, vai chamar este item
    getProfile()//e este item
  }

  fun getTransactions() = viewModelScope.launch { //getTransactions vai executar o nosso flow que esta em coroutines
    repository.getTransactions().collectLatest { //collectLatest(Pega o último resultado e joga para a nossa tela)
      _transactions.value = it //vai fazer a mudança de estado, sair do empty(vazio) e trazer um item novo
    }
  }

  fun getProfile() = viewModelScope.launch {
    repository.getProfile().collectLatest {
      _profile.value = it
    }
  }
}
