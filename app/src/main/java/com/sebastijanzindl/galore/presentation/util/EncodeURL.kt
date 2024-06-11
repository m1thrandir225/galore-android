package com.sebastijanzindl.galore.presentation.util

import java.net.URLEncoder

fun EncodeURL(url: String) = URLEncoder.encode(url, "utf-8")

