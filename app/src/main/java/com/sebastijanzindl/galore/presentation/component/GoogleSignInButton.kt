package com.sebastijanzindl.galore.presentation.component


import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.presentation.viewmodels.AuthSharedViewModel
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.UUID

@Composable
fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    viewModel: AuthSharedViewModel,
    onSuccessCallback: () -> Unit,
) {
    val context = LocalContext.current;
    val coroutineScope =  rememberCoroutineScope()
    val postgrest = Postgrest;
    val onClick: () -> Unit = {
        val credentialManager = CredentialManager.create(context)

        val rawNonce = UUID.randomUUID().toString();
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it)}

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("243120785119-dq5o2t3v582v57raap8k3od9p8r8lbob.apps.googleusercontent.com")
            .setNonce(hashedNonce)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        coroutineScope.launch {
           try {
               val result = credentialManager.getCredential(
                   request = request,
                   context = context,
               )
               val credential = result.credential

               val googleIdTokenCredential = GoogleIdTokenCredential
                   .createFrom(credential.data)
               val googleIdToken = googleIdTokenCredential.idToken

               viewModel.signInWithGoogle(token = googleIdToken, rawNonce = rawNonce);

               Log.i("wow", googleIdToken)
               Toast.makeText(context, "You are signed in !", Toast.LENGTH_SHORT).show()

               onSuccessCallback();
           } catch (e: GoogleIdTokenParsingException) {
               Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
           } catch (e: Exception) {
               Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
           }
        }
    }

     Button(
         onClick = onClick,
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