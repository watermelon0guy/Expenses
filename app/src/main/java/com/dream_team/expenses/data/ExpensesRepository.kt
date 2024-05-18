package com.dream_team.expenses.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface ExpensesRepository {
    suspend fun insert(expense: Expense)
    suspend fun update(expense: Expense)
    suspend fun delete(expense: Expense)
    suspend fun getExpenseByDateTime(dateTime: LocalDateTime): Expense?
    suspend fun getAllExpenses(): Flow<List<Expense>>
}
