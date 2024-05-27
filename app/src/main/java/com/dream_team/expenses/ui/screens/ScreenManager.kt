package com.dream_team.expenses.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dream_team.expenses.R
import com.dream_team.expenses.view_models.AllExpensesViewModel
import com.dream_team.expenses.view_models.AddExpenseViewModel

enum class Screens {
    ALL_EXPENSES,
    ADD_EXPENSE,
    GRAPHS
}

@Composable
fun ScreenManager(
    allExpensesViewModel: AllExpensesViewModel,
    addExpensesViewModel: AddExpenseViewModel
) {
    var currentScreen by remember { mutableStateOf(Screens.ADD_EXPENSE) }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { currentScreen = Screens.ALL_EXPENSES },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(id = R.string.all_expenses))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { currentScreen = Screens.ADD_EXPENSE },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(id = R.string.add_expense))
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (currentScreen) {
                Screens.ALL_EXPENSES -> AllExpensesScreen(viewModel = allExpensesViewModel)
                Screens.ADD_EXPENSE -> AddExpenseScreen(viewModel = addExpensesViewModel)
                Screens.GRAPHS -> TODO()
            }
        }
    }
}