package com.dream_team.expenses.data

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it, formatter) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun fromExpenseCategory(value: ExpenseCategory?): String? {
        return value?.name
    }

    @TypeConverter
    @JvmStatic
    fun toExpenseCategory(value: String?): ExpenseCategory? {
        return value?.let { ExpenseCategory.valueOf(it) }
    }
}
