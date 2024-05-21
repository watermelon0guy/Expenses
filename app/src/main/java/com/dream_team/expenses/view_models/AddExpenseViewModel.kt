package com.dream_team.expenses.view_models

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream_team.expenses.ExpenseApplication
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.data.ExpenseCategory
import com.dream_team.expenses.data.ExpensesRepositoryDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date

class ExpenseViewModel : ViewModel() {
    private val repository: ExpensesRepositoryDB = ExpenseApplication.repository

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
        val expense = Expense(value = _expenseValue.value, category = _selectedCategory.value, LocalDateTime.now())
        Log.d("Expense", "Добавлен расход: ${expense.value}, Категория: ${expense.category}")
        viewModelScope.launch {
            repository.insert(expense)
        }
        // Очищаем поля после добавления расхода
        _expenseValue.value = 0.0
        _selectedCategory.value = ExpenseCategory.FOOD
    }

//    TODO("Снести функцию ниже")
    fun debugPrintAllDB() {
        viewModelScope.launch {
            repository.getAllExpenses().collect { value -> Log.d("DB", "${value.toString()}") }
        }
    }
}