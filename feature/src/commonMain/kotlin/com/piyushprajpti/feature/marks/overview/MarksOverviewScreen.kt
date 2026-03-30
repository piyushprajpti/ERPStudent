package com.piyushprajpti.feature.marks.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.marks.components.AcademicPeriodSelector
import com.piyushprajpti.feature.marks.components.AggregatePercentageCard
import com.piyushprajpti.feature.marks.components.CgpaOverviewCard
import com.piyushprajpti.feature.marks.components.SubjectCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MarksOverviewScreen(
    onBackClick: () -> Unit,
    onGradeCardClick: () -> Unit,
    viewModel: MarksOverviewViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "My Marks"
        )

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                AcademicPeriodSelector(
                    selectedPeriod = state.selectedPeriod,
                    academicPeriods = state.academicPeriods,
                    onPeriodSelected = { viewModel.onEvent(MarksOverviewEvent.OnPeriodSelected(it)) }
                )
            }

            item {
                CgpaOverviewCard(
                    cgpa = state.cgpa,
                    maxCgpa = state.maxCgpa,
                    percentile = state.percentile
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Subject Breakdown",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    IconButton(onClick = { /* Handle filter/sort */ }) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Filter",
                            tint = CustomTheme.colors.primaryText
                        )
                    }
                }
            }

            items(state.subjects) { subject ->
                SubjectCard(subject = subject)
            }

            item {
                AggregatePercentageCard(
                    percentage = state.aggregatePercentage,
                    onViewGradeCardClick = onGradeCardClick,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}
