package com.dream_team.expenses.data

import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dream_team.expenses.R
import java.time.LocalDateTime

@Entity(tableName = "expenses")
data class Expense(
    val value: Double,
    val category: ExpenseCategory,
    @PrimaryKey val dateTime: LocalDateTime
)

enum class ExpenseCategory(
    @StringRes val categoryNameResId: Int,
    val imageResId: Int
) {
    FOOD(R.string.category_food, R.drawable.food),
    TRANSPORTATION(R.string.category_transportation, R.drawable.transportation),
    ENTERTAINMENT(R.string.category_entertainment, R.drawable.entertaiment),
    UTILITIES(R.string.category_utilities, R.drawable.utilities),
    SHOPPING(R.string.category_shopping, R.drawable.shopping),
    OTHER(R.string.category_other, R.drawable.other);
}