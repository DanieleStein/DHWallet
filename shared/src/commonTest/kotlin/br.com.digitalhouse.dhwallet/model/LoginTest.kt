package br.com.digitalhouse.dhwallet.model

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoginTest {
    lateinit var login: Login //instanciando Login//Lateinit para quando queremos colocar o valor da variavel depois(no caso os parametros do Login)

    @BeforeTest
    fun before() { //Before, quando quisermos que ele execute o login antes de cada teste
        //Entrada
        login = Login("usuario@kmm.com", "12345")
    }
    @Test
    fun dadoLoginCorretoTrue() {

        //Processamento
        val isValid = login.validador()

        //Resposta
        assertTrue { isValid }

    }

    @Test
    fun dadoLoginIncorretoFalse() {
        //Problema de usar o Before, é que o valor no login esta correto, e aqui neste teste precisamos de um valor incorreto para ele nos retornar falso
        //Podemos fazer desta forma
        login = login.copy("teste@teste.com") //Pegando o nosso login, e fazendo uma cópia, e pegando apenas um dos parametros
                                                      //Essa copia do login só fica disponivel quando eu altero a classe Login para (data class Login)

        //Processamento
        val isValid = login.validador()

        //Resposta
        assertFalse(isValid)
    }

}