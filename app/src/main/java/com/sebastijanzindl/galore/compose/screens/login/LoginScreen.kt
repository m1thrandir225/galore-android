package com.sebastijanzindl.galore.compose.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.compose.components.Logo
import com.sebastijanzindl.galore.compose.components.GoogleSigninButton
import com.sebastijanzindl.galore.ui.theme.GaloreTheme
import com.sebastijanzindl.galore.viewmodels.LoginScreenViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.login_lottie))
    val scrollState = rememberScrollState();
    val focusManager = LocalFocusManager.current;

    Scaffold(modifier = modifier) {
            contentPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =  Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding(),
                    start = 24.dp,
                    end = 24.dp
                )
                .verticalScroll(scrollState)
                .imePadding()
                .imeNestedScroll()

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Logo(modifier = Modifier.align(Alignment.TopCenter))
                LottieAnimation(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp), composition = composition, iterations = LottieConstants.IterateForever)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text ="Login",
                    modifier = Modifier.padding(bottom = 12.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.email,
                    onValueChange = { newValue -> viewModel.updateEmail(newValue) },
                    label = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                    ),
                    trailingIcon = {
                                   if(viewModel.hasEmailError)
                                       Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                    },
                    supportingText = {
                                     if(viewModel.hasEmailError) {
                                        Text("Email is invalid")
                                     }
                    },
                    singleLine = true,
                    isError = viewModel.hasEmailError
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.password,
                    onValueChange = { newValue -> viewModel.updatePassword(newValue) },
                    label = {
                        Text("Password")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            viewModel.loginUser()
                        }
                    ),
                    singleLine = true,
                    trailingIcon = {
                                   if(viewModel.hasPasswordError) {
                                       Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                                   }
                    },
                    supportingText = {
                                     if(viewModel.hasPasswordError) {
                                        Text(text = "Password must be at least 8 characters long.")
                                     }
                    },
                    isError = viewModel.hasPasswordError

                )
                Text(
                    text = "Forgot your password ?",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
            Button(
                enabled = !viewModel.hasEmailError && !viewModel.hasPasswordError,
                onClick = { viewModel.loginUser() },
                modifier = Modifier
                    .padding(top = 50.dp, bottom = 12.dp)
                    .fillMaxWidth()

            ) {
                Text(text = "Continue")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Text(
                    text = "Don't have an account ?",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier.clickable(
                        onClick = onRegisterClick
                    ),
                    text = "Register",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Box(modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 31.dp)
                    )
                    .width(138.dp)
                    .height(3.dp)
                )
                Text(
                    text = "or",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Box(modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 31.dp)
                    )
                    .width(138.dp)
                    .height(3.dp)
                )
            }
          GoogleSigninButton(viewModel = viewModel)
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun LoginScreenPreview() {
    GaloreTheme {
        LoginScreen(onRegisterClick = {}, viewModel = hiltViewModel<LoginScreenViewModel>())
    }
}