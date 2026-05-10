package com.shvarsman.financecalculator.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shvarsman.financecalculator.getPlatformType
import com.shvarsman.financecalculator.strings.getStrings
import com.shvarsman.financecalculator.theme.DefaultGraphStyle
import com.shvarsman.financecalculator.util.formatMoney
import com.shvarsman.financecalculator.viewmodel.CalculatorViewModel
import com.shvarsman.financecalculator.ui.components.*

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    val state by viewModel.state.collectAsState()
    val strings = getStrings(state.language)
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LanguageSelector(
                currentLang = state.language,
                onSelect = { viewModel.onLanguageChange(it) }
            )

            AmountField(
                value = state.principal,
                onValueChange = { viewModel.onPrincipalChange(it) },
                label = strings.principalLabel,
                enabled = !state.isLoading
            )
            RateField(
                value = state.rate,
                onValueChange = { viewModel.onRateChange(it) },
                label = strings.rateLabel,
                enabled = !state.isLoading
            )
            TermField(
                value = state.years,
                onValueChange = { viewModel.onYearsChange(it) },
                label = strings.yearsLabel,
                enabled = !state.isLoading
            )
            CapitalizationSelector(
                current = state.compounding,
                onSelect = { viewModel.onCompoundingChange(it) },
                label = strings.compoundingLabel
            )
            CalculateButton(
                onClick = { viewModel.calculate() },
                text = strings.calculateButton,
                enabled = !state.isLoading
            )

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }

            state.errorMessage?.let { error ->
                Text(text = error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            AnimatedVisibility(
                visible = state.result != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                state.result?.let { result ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("${strings.resultFinalAmount}: ${formatMoney(result.finalAmount)}")
                        Text("${strings.resultTotalProfit}: ${formatMoney(result.totalProfit)}")
                        Spacer(Modifier.height(8.dp))
                        GraphView(
                            balances = result.yearlyBalances,
                            maxValue = result.finalAmount,
                            style = when (getPlatformType()) {
                                "android" -> DefaultGraphStyle.androidStyle
                                "ios" -> DefaultGraphStyle.iosStyle
                                "desktop" -> DefaultGraphStyle.desktopStyle
                                "web" -> DefaultGraphStyle.webStyle
                                else -> DefaultGraphStyle.desktopStyle
                            }
                        )
                    }
                }
            }
        }
    }
}