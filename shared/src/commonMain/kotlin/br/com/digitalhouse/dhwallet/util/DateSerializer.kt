package br.com.digitalhouse.dhwallet.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DateSerializer: KSerializer<String> {//Vai extender de KSerialLizer(classe pronta) e o retorno é String

  //Descripitor(vamos transformar nos nossos tipos primitivos(Vai ser nosso Date de String
  override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

  //Vamos receber um valor do tipo LocalDate, e converter ele para String(Quando receber da api vem com /, e quando mandar para api vai com -)
  override fun serialize(encoder: Encoder, value: String) {
    val date = value.split('-')//Split-Quebra a nossa String e transforma cada bloco que esta entre os traços em Lista de String
    encoder.encodeString("${date[2]}/${date[1]}/${date[0]}")//impimir a data em forma(dia, mês, ano)
  }

  //Vamos receber um valor do tipo String, e converter ele para localDate(Quando mandar para api vai com -, e quando receber da api vem com/)
  override fun deserialize(decoder: Decoder) = decoder.decodeString().replace("-", "/")
}
