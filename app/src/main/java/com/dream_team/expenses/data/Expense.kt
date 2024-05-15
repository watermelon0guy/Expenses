package com.dream_team.expenses.data

import androidx.annotation.StringRes
import com.dream_team.expenses.R

data class Expense(
    val value: Double,
    val category: ExpenseCategory
)


enum class ExpenseCategory(@StringRes val categoryNameResId: Int) {
    FOOD(R.string.category_food),
    TRANSPORTATION(R.string.category_transportation),
    ENTERTAINMENT(R.string.category_entertainment),
    UTILITIES(R.string.category_utilities),
    SHOPPING(R.string.category_shopping),
    OTHER(R.string.category_other);
}