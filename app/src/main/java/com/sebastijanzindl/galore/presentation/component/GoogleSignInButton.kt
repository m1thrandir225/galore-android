package com.sebastijanzindl.galore.presentation.component


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.viewmodels.AuthSharedViewModel
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    viewModel: AuthSharedViewModel,
    onSuccessCallback: () -> Unit,
) {
    val context = LocalContext.current


    val googleSignIn = viewModel.supabaseComposeAuth.rememberSignInWithGoogle(
        onResult = { result -> //optional error handling
            when (result) {
                is NativeSignInResult.Success -> {
                    onSuccessCallback()
                }
                is NativeSignInResult.ClosedByUser -> {
                    Toast.makeText(context, "Google Signing Closed by user", Toast.LENGTH_SHORT).show()
                }
                is NativeSignInResult.Error -> {
                    Toast.makeText(context, "Error While Signing in: $result", Toast.LENGTH_SHORT).show()
                }
                is NativeSignInResult.NetworkError -> {
                    Toast.makeText(context, "Network error while signing-in", Toast.LENGTH_SHORT).show()
                }
            }
        },
    )

     Button(
         onClick = { googleSignIn.startFlow() },
         colors = ButtonDefaults.buttonColors(
             MaterialTheme.colorScheme.secondary
         ),
         modifier = modifier.fillMaxWidth()
     ) {
         Row(
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.spacedBy(8.dp)
         ) {
             Icon(painter = painterResource(R.drawable.google_logo), contentDescription = "")
             Text(
                 text = "Continue With Google"
             )
         }
     }
}