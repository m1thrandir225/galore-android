package com.sebastijanzindl.galore.presentation.util

import android.content.Context
import android.provider.Settings

fun Context.deviceId() = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
