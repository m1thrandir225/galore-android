package com.sebastijanzindl.galore.presentation.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateUtil {
    fun convertEpochMilisecondsToDate(milis: Long) : LocalDateTime  {
        return Instant
            .fromEpochMilliseconds(milis)
            .toLocalDateTime(TimeZone.UTC)
    }
}