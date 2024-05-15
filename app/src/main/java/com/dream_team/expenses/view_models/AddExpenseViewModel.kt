package com.dream_team.expenses.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.data.ExpenseCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ExpenseViewModel : ViewModel() {
    private val _expenseValue = MutableStateFlow(0.0)
    val expenseValue: StateFlow<Double> = _expenseValue

    private val _selectedCategory = MutableStateFlow(ExpenseCategory.FOOD)
    val selectedCategory: StateFlow<ExpenseCategory> = _selectedCategory

    fun setExpenseValue(value: Double) {
        _expenseValue.value = value
    }

    fun setSelectedCategory(category: ExpenseCategory) {
        _selectedCategory.value = category
    }

    fun addExpense() {
        val expense = Expense(value = _expenseValue.value, category = _selectedCategory.value)
        Log.d("Expense", "Добавлен расход: ${expense.value}, Категория: ${expense.category.categoryNameResId}")
        // Очищаем поля после добавления расхода

        _expenseValue.value = 0.0
        _selectedCategory.value = ExpenseCategory.FOOD
    }
}