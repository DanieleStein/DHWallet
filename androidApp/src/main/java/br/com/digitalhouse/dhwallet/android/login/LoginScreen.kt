package br.com.digitalhouse.dhwallet.android.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.digitalhouse.dhwallet.android.MyApplicationTheme
import br.com.digitalhouse.dhwallet.android.Route
import br.com.digitalhouse.dhwallet.android.component.AlertDialogComponent
import br.com.digitalhouse.dhwallet.model.Login

@Composable
fun LoginScreen(onHomeNavigate: () -> Unit) {
    MyApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val login = remember { mutableStateOf(TextFieldValue()) }
                val openDialog = remember { mutableStateOf(false) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val passwordVisible = remember { mutableStateOf(false) }

                Text(text = "Login", fontWeight = FontWeight.Bold, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "E-mail") },
                    value = login.value,
                    onValueChange = {login.value = it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Senha") },
                    value = password.value,
                    onValueChange = {password.value = it},
                    visualTransformation = if(passwordVisible.value.not()) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val description = if(passwordVisible.value.not()) "Visivel" else "Invisivel"
                        val icone = if(passwordVisible.value.not()) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { passwordVisible.value = passwordVisible.value.not() }) {
                            Icon(imageVector = icone, contentDescription = description)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val conferirLogin = Login(
                        usuario = login.value.text,
                        senha = password.value.text
                    )
                    if(conferirLogin.validador()) {
                        onHomeNavigate.invoke()
                    } else {
                        openDialog.value = true
                    }

                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Entrar")
                }
                AlertDialogComponent(
                    openDialog = openDialog.value,
                    email = "Ops! Login e/ou Senha inválida.",
                    onDismissRequest = {openDialog.value = false}
                )
            }
        }
    }

}

@Preview
@Composable
fun DefaultPreview() {
    LoginScreen {}
}