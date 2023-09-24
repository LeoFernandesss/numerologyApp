package io.github.leofernandesss.numerologiaads.presenter.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NumerologyScreen() {//Nessa função com os observables eu mantenho das váriaveis name e result, então sempre que ambos sofrerem alterações a tela mudará automaticamente.
    val viewModel: HomeViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier.padding(50.dp),
            value = name,
            onValueChange = { newName ->
                name = newName
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    localSoftwareKeyboardController?.hide()
                    result = if (viewModel.isValidName(name).first) {
                        val numerologyNumber = viewModel.calculateNumerology(name)
                        viewModel.getNumerologyExplanation(numerologyNumber)
                    } else {
                        viewModel.isValidName(name).second
                    }
                }
            ),
            placeholder = {
                Text(
                    text = "Nome completo",
                    style = TextStyle(
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                )
            },
            singleLine = true
        )
        Button(// Nesse botão eu adicionei local.hide para esconder o teclado quando clicar no botão, e após clicado ele verificar se o nome tem uma condição verdadeira,se tiver ele retornar True e se não houver, ele retonar false.
            onClick = {
                localSoftwareKeyboardController?.hide()
                result = if (viewModel.isValidName(name).first) {
                    val numerologyNumber = viewModel.calculateNumerology(name)
                    viewModel.getNumerologyExplanation(numerologyNumber)
                } else {
                    viewModel.isValidName(name).second
                }
            },
            modifier = Modifier
                .padding(top = 16.dp),
        ) {
            Text(
                text = "Calcular Numerologia",
                style = TextStyle(
                    color = Color.Black
                )
            )
        }
        if (result.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = result,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ScreenPreview() {
    NumerologyScreen()
}