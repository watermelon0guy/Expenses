package com.dream_team.expenses.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream_team.expenses.ExpenseApplication
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.data.ExpensesRepository
import com.dream_team.expenses.data.ExpensesRepositoryDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllExpensesViewModel : ViewModel() {
    private val repository: ExpensesRepositoryDB = ExpenseApplication.repository
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses

    init {
        viewModelScope.launch {
            repository.getAllExpenses().collect { expenseList ->
                _expenses.value = expenseList
            }
        }
    }
}