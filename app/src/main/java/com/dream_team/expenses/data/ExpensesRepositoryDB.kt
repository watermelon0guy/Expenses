package com.dream_team.expenses.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class ExpensesRepositoryDB(private val expenseDao: ExpenseDao) : ExpensesRepository {

    override suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    override suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    override suspend fun delete(expense: Expense) {
        expenseDao.delete(expense)
    }

    override suspend fun getExpenseByDateTime(dateTime: LocalDateTime): Expense? {
        return expenseDao.getExpenseByDateTime(dateTime)
    }

    override suspend fun getAllExpenses(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }
}