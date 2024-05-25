package com.dream_team.expenses.data

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Flatware
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.Flatware
import androidx.compose.material.icons.rounded.House
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.TheaterComedy
import androidx.compose.ui.graphics.vector.ImageVector
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
    val imageResId: ImageVector
) {
    FOOD(R.string.category_food, Icons.Rounded.Flatware),
    TRANSPORTATION(R.string.category_transportation, Icons.Rounded.DirectionsBus),
    ENTERTAINMENT(R.string.category_entertainment, Icons.Rounded.TheaterComedy),
    UTILITIES(R.string.category_utilities, Icons.Rounded.House),
    SHOPPING(R.string.category_shopping, Icons.Rounded.ShoppingBag),
    OTHER(R.string.category_other, Icons.Rounded.Category);
}