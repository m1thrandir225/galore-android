package com.sebastijanzindl.galore.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sebastijanzindl.galore.R
import com.sebastijanzindl.galore.domain.models.UserProfile
import com.sebastijanzindl.galore.ui.theme.GaloreTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    userProfile: UserProfile?,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    refetchProfile: () -> Unit,
    menuItems: @Composable () -> Unit,
) {
    LaunchedEffect(key1 = sheetState) {
        refetchProfile();
    }
    ModalBottomSheet(
        modifier = modifier.height(500.dp),
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(1050.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (userProfile != null) {
                UserInfoShowcase(
                    fullName = userProfile.fullName,
                    email = userProfile.email,
                    avatarUrl = userProfile.avatarUrl
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
               menuItems()
            }

//            Spacer(
//                Modifier.windowInsetsBottomHeight(
//                    WindowInset
//                )
//            )
        }
    }
}

@Composable
fun UserInfoShowcase(
    fullName: String,
    email: String,
    avatarUrl: String?
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
            ,
            model = avatarUrl,
            contentDescription = "Your Avatar",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.profile_picture_placeholder)
        )
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
                )
            Text(
                text = email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



@Preview(apiLevel = 33)
@Composable
private fun UserInfoPreview() {
    GaloreTheme {
        UserInfoShowcase(fullName = "Sebastijan Zindl", "sebastijanprime32@gmail.com", null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(apiLevel = 33)
@Composable
private fun BottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true)
    var sheetOpen by remember {
        mutableStateOf(true)
    }

    val onDismissRequest = {
        sheetOpen = false
    }
    val userProfile = UserProfile("1", avatarUrl = "", email = "sebastijan.zindl@protonmail.com", fullName = "Sebastijan Zindl", updatedAt = "", pushNotifications = false, emailNotifications = false)
    GaloreTheme {
        ProfileBottomSheet(
            modifier = Modifier,
            userProfile = userProfile,
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            refetchProfile = {},
            menuItems = {
                MenuItem(
                    buttonIcon = ButtonComposableWrapper { Icon(Icons.Default.Settings, "") },
                    title = "Settings") {
                    println("Settings Clicked")
                }
                MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.question_mark_24px), "") }, title = "Help") {
                    println("Settings Clicked")
                }
                MenuItem(buttonIcon = ButtonComposableWrapper {  Icon(painterResource(id = R.drawable.logout_24px), "") }, title = "Logout") {
                    println("Settings Clicked")
                }
            }
        )
    }
}