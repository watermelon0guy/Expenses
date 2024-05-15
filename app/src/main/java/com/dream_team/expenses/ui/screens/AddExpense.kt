package com.dream_team.expenses.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dream_team.expenses.R
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.data.ExpenseCategory
import com.dream_team.expenses.view_models.ExpenseViewModel

@Composable
fun AddExpenseScreen(viewModel: ExpenseViewModel) {
    val expenseValue by viewModel.expenseValue.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = if (expenseValue == 0.0) "" else expenseValue.toBigDecimal().toPlainString(),
            onValueChange =
            {
                if (!it.contains("[.,].*[.,]".toRegex())) viewModel.setExpenseValue(it.toDouble())
            },
            label = { Text(stringResource(id = R.string.expense)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        CategoryMenu(selectedCategory = selectedCategory, onCategorySelected = { viewModel.setSelectedCategory(it) })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.addExpense() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.add_expense), fontSize = 18.sp)
        }
    }
}


@Composable
fun CategoryMenu(selectedCategory: ExpenseCategory, onCategorySelected: (ExpenseCategory) -> Unit) {
    val categories = ExpenseCategory.entries.toTypedArray()

    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Filled.Menu, contentDescription = null)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(stringResource(id = category.categoryNameResId)) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}
