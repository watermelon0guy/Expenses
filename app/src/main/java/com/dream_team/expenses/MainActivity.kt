package com.dream_team.expenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dream_team.expenses.ui.screens.AddExpenseScreen
import com.dream_team.expenses.ui.screens.AllExpensesScreen
import com.dream_team.expenses.ui.theme.ExpensesTheme
import com.dream_team.expenses.view_models.AllExpensesViewModel
import com.dream_team.expenses.view_models.ExpenseViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        val addExpenseViewModel: ExpenseViewModel by viewModels()
        val allExpensesViewModel: AllExpensesViewModel by viewModels()
        setContent {
            ExpensesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    AddExpenseScreen(addExpenseViewModel)
                    AllExpensesScreen(viewModel = allExpensesViewModel)
                }
            }
        }
    }
}
