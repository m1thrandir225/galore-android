package com.sebastijanzindl.galore.presentation.util

import java.net.URLDecoder

fun DecodeUrl(url: String) = URLDecoder.decode(url, "utf-8")