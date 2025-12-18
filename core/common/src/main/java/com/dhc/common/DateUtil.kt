package com.dhc.common

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

object DateUtil {
    /**
     * 자정까지 남은 시간 초로 계산
     */
    fun getTimeUntilMidnight(): Long {
        val koreaZone = ZoneId.of("Asia/Seoul")
        val now = ZonedDateTime.now(koreaZone)
        val midnight = now.plusDays(1).toLocalDate().atStartOfDay(koreaZone)

        val secondsRemaining = ChronoUnit.SECONDS.between(now, midnight)

        return secondsRemaining
    }


    fun formatSecondsToTime(totalSeconds: Long): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}