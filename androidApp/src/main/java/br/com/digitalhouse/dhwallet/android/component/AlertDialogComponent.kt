package br.com.digitalhouse.dhwallet.android.component

import android.app.AlertDialog
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogComponent(
    openDialog: Boolean,
    email: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(openDialog) {
        AlertDialog(
            modifier = modifier,
            title = { /*Text(text = "Ops!", color = Color.White)*/ },
            text = { Text(text = "$email", color = Color.White) },
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Ok", color = Color.White)
                }
            },
            backgroundColor = Color(0xFFBD22D8),
            contentColor = Color.White
        )
    }
}
@Preview
@Composable
fun AlertDialogComponent_Preview() {
    AlertDialogComponent(openDialog = true, email = "Teste", onDismissRequest = { /*TODO*/ })
}