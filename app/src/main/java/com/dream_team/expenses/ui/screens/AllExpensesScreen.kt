@file:Suppress("DEPRECATION")

package com.dream_team.expenses.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.rememberSwipeableState
import com.dream_team.expenses.R
import com.dream_team.expenses.data.Expense
import com.dream_team.expenses.view_models.AllExpensesViewModel
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalWearMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun AllExpensesScreen(
    viewModel: AllExpensesViewModel,
    onNavigateToStatistics: () -> Unit
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
                ExpenseItem(expense)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseItem(
    expense: Expense,
) {
            Card(
                modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(id = expense.category.categoryNameResId))
                    Text(text = "${expense.value}")
                    Text(
                        text = expense.dateTime
                            .atZone(ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Box(
                    modifier = Modifier.size(70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = expense.category.imageResId),
                        contentDescription = stringResource(id = expense.category.categoryNameResId)
                    )
                }
            }
        }
    }

