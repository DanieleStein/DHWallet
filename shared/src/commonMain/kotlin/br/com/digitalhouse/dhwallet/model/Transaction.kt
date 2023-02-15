package br.com.digitalhouse.dhwallet.model

import br.com.digitalhouse.dhwallet.util.DateSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable //Para dizer que eu quero serializar a transação, transformar nosso objeto em JSON
data class Transaction(
  val logo: String,
  val title: String,
  val transactionType: TransactionType, //nossa lista que só tem as opcoes da lista(enum class)
  val value: Double,
  @Serializable(with = DateSerializer::class)//no momento que a nossa data for serializada, ele vai usar o nosso Objeto DateSerializer(passando a data formatada "dd/mm/aaaa"
  val date: String //Data da transação
)

enum class TransactionType(val description: String) {
  CREDIT("Crédito"),
  DEBIT("Pagamento")
}
