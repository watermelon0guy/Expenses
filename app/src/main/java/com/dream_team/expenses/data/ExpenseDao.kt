package com.dream_team.expenses.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses WHERE dateTime = :dateTime")
    suspend fun getExpenseByDateTime(dateTime: LocalDateTime): Expense?

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): Flow<List<Expense>>
}