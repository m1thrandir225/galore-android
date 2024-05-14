package com.sebastijanzindl.galore.presentation.screens.register


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
import com.sebastijanzindl.galore.presentation.component.GoogleSignInButton
import com.sebastijanzindl.galore.presentation.component.Logo
import com.sebastijanzindl.galore.presentation.viewmodels.AuthSharedViewModel
import com.sebastijanzindl.galore.ui.theme.GaloreTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterScreenViewModel = hiltViewModel(),
    sharedViewModel: AuthSharedViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToOnboarding: () -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.register_lottie))
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val registerUser = {
        navigateToOnboarding()
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 24.dp,
                end = 24.dp
            )
            .imePadding()
            .imeNestedScroll()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ){
            Logo(modifier = Modifier.align(Alignment.TopCenter))
            LottieAnimation(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                ,composition = composition, iterations = LottieConstants.IterateForever)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text ="Register",
                modifier = Modifier.padding(bottom = 12.dp),
                style = MaterialTheme.typography.headlineMedium
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.fullName,
                onValueChange = { newValue -> viewModel.updateFullName(newValue) },
                label = {
                    Text("Name")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                trailingIcon = {
                    if(viewModel.hasFullNameError) {
                        Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    if(viewModel.hasFullNameError) {
                        Text(text = "Field must not be empty.")
                    }
                },
                isError = viewModel.hasFullNameError
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.email,
                onValueChange = { newValue -> viewModel.updateEmail(newValue) },
                label = {
                    Text(text = "Email")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction =  ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                trailingIcon = {
                    if(viewModel.hasEmailError) {
                        Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    if(viewModel.hasEmailError) {
                        Text(text = "Field is not valid.")
                    }
                },
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
                        viewModel.registerUser(successCallback = registerUser)
                    }
                ),
                trailingIcon = {
                    if(viewModel.hasPasswordError) {
                        Icon(Icons.Filled.Info, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    if(viewModel.hasPasswordError) {
                        Text(text = "Field must be at least 8 characters long.")
                    }
                },
                isError = viewModel.hasPasswordError
            )
        }
        Button(
            onClick = { viewModel.registerUser(successCallback = registerUser) },
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()

        ) {
            Text(text = "Continue")
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = "Already have an account ?",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier.clickable(
                    onClick = navigateToLogin
                ),
                text = "Login",
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

        GoogleSignInButton(viewModel = sharedViewModel, onSuccessCallback = registerUser)
    }
}

@Preview(apiLevel = 33)
@Composable
private fun RegisterScreenPreview() {
    GaloreTheme {
        RegisterScreen(navigateToLogin = {}, navigateToOnboarding = {})
    }
}