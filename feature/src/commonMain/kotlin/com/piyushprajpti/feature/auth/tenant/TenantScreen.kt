package com.piyushprajpti.feature.auth.tenant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.utils.loading_helper.CircularLoading
import com.piyushprajpti.feature.utils.PrimaryActionButton
import com.piyushprajpti.feature.utils.SearchBox
import com.piyushprajpti.feature.utils.SelectableCard
import com.piyushprajpti.feature.utils.loading_helper.LoadingHelper
import com.piyushprajpti.feature.utils.loading_helper.LoadingWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TenantScreen(
    viewModel: TenantViewModel = koinViewModel(),
    onContinueClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onContinueClick() {
        viewModel.onEvent(TenantEvent.OnContinueClick(onContinueClick))
    }

    LoadingWidget(viewModel.loadingHelper.state.value)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
//        if (state.isLoading) CircularLoading(70.dp)

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Welcome Student",
                color = CustomTheme.colors.primaryText,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
            )

            Text(
                text = "Choose your university/college to continue",
                color = CustomTheme.colors.secondaryText,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
            )

            Spacer(Modifier.height(20.dp))

            SearchBox(
                placeholderText = "Search Institutions",
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(TenantEvent.OnSearchQueryChange(it)) },
                onClearClick = { viewModel.onEvent(TenantEvent.OnSearchQueryChange("")) }
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp)
            ) {
                items(state.originalTenantList) { tenant ->
                    SelectableCard(
                        title = tenant.name,
                        subtitle = tenant.address,
                        icon = tenant.logo,
                        isSelected = tenant.id == state.selectedTenant?.id,
                        onClick = { viewModel.onEvent(TenantEvent.OnTenantChange(tenant)) },
                    )
                }
            }

            PrimaryActionButton(
                text = "Continue",
                onClick = { onContinueClick() },
                isLoading = state.isLoading,
                enabled = state.selectedTenant != null && !state.isLoading,
            )
        }
    }
}
