package com.dream_team.expenses

import android.app.Application
import com.dream_team.expenses.data.AppDatabase
import com.dream_team.expenses.data.ExpensesRepository
import com.dream_team.expenses.data.ExpensesRepositoryDB

class ExpenseApplication :Application() {

    companion object {
        lateinit var database: AppDatabase
        lateinit var repository: ExpensesRepositoryDB
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize the database here, when the Application context is ready.
        database = AppDatabase.getDatabase(this)
        repository = ExpensesRepositoryDB(database.expenseDao())
    }
}