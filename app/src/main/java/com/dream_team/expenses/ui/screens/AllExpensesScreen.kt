package com.dream_team.expenses.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dream_team.expenses.R
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.view_models.AllExpensesViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllExpensesScreen(
    viewModel: AllExpensesViewModel
) {
    val expenses by viewModel.expenses.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Expenses") },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(expenses) { expense ->
                ExpenseItem(expense, { viewModel.deleteExpense(expense) })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseItem(
    expense: Expense,
    onDelete: () -> Unit
) {
    val shape = RoundedCornerShape(10.dp)
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .animateContentSize()
            .combinedClickable(
                onClickLabel = stringResource(id = R.string.expense_details),
                onClick = { expanded = !expanded },
                onLongClick = { onDelete() }
            )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Icon(
                    imageVector = expense.category.imageResId,
                    contentDescription = stringResource(id = expense.category.categoryNameResId),
                    modifier = Modifier.size(40.dp)
                )
                AnimatedVisibility(expanded) {
                    Text(
                        text = stringResource(expense.category.categoryNameResId),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${expense.value}",
                    style = MaterialTheme.typography.headlineLarge
                )
                AnimatedVisibility(expanded) {
                    Text(
                        text = expense.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

