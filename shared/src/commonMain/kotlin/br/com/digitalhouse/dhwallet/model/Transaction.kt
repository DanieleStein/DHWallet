package br.com.digitalhouse.dhwallet.model

import br.com.digitalhouse.dhwallet.util.DateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable //Para dizer que eu quero serializar a transação, transformar nosso objeto em JSON
data class Transaction( //Todos os valores aqui, estão batendo com a nossa api(não teremos erro de serialização
  val id: Int,
  val logo: String,
  val title: String,
  val background: String,
  @SerialName("type") //Colocamos isso para informar o nome que vem da nossa api
  val transactionType: TransactionType, //nossa lista que só tem as opcoes da lista(enum class)
  val value: Double,
  //@Serializable(with = DateSerializer::class)//no momento que a nossa data for serializada, ele vai usar o nosso Objeto DateSerializer(passando a data formatada "dd/mm/aaaa"
  @SerialName("createdAt")//Colocamos isso para informar o nome que vem da nossa api
  val date: String //Data da transação
)

@Serializable
data class TransectionResponse(val transactions: List<Transaction>)

enum class TransactionType(val description: String) {
  CREDIT("Crédito"),
  DEBIT("Pagamento")
}
