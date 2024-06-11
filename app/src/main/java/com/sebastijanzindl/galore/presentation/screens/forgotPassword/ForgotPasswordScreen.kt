package com.sebastijanzindl.galore.presentation.screens.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.component.SnackbarMessageHandler
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ForgotPasswordScreenViewModel = hiltViewModel(),
    goBack: () -> Unit
) {

    val isLoading by viewModel.isLoading.collectAsState()

    val toastMessage by viewModel.toastMessage.collectAsState()

    val magicLinkSent by viewModel.magicLinkSent.collectAsState()

    var email by remember {
        mutableStateOf("")
    }

    SnackbarMessageHandler(snackbarMessage = toastMessage, onDismissSnackbar = { viewModel.dismissToast() })

    Column (
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 24.dp)
    ) {
        Logo()
        when(magicLinkSent) {
            true -> {
                SuccessfulMagicLinkRequest(goBack = goBack)
            }
            false -> {
                Text(text = "There was an error sending the password reset link.")
            }
            else -> {
                Column (
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Reset Your Password",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.SemiBold
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email,
                        onValueChange = { newValue -> email = newValue.trimEnd {
                            it == ' '
                        }},
                        label = {
                            Text(text = "Your Email")
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Email
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if(email.isNotEmpty()) {
                                    viewModel.sendPasswordResetRequest(email)
                                }
                            }
                        )
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        enabled = email.isNotEmpty() || !isLoading,
                        onClick = {
                            if(email.isNotEmpty()) {
                                viewModel.sendPasswordResetRequest(email)
                            }
                        }
                    ) {
                        if(isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                trackColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        } else {
                            Text(text = "Send Magic Link")
                        }
                    }
                }
        }
}
        Spacer(modifier = modifier.padding(2.dp))
    }
}

@Composable
fun SuccessfulMagicLinkRequest(
    modifier: Modifier = Modifier,
    goBack: () -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.reset_password_success))
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            iterations = 1
        )
        Spacer(modifier = Modifier.padding(4.dp))

        Button(onClick = { goBack() }) {
            Text(text = "Go Back")
        }
    }
   
}

@Preview
@Composable
private fun ForgotPasswordScreenPreview() {
    GaloreTheme {
        ForgotPasswordScreen() {}
    }
}