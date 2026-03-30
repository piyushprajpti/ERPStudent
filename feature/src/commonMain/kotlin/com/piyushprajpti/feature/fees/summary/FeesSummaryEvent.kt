package com.piyushprajpti.feature.fees.summary

sealed interface FeesSummaryEvent {
    data object OnBackClick : FeesSummaryEvent
    data object OnPayNowClick : FeesSummaryEvent
    data class OnFeeItemClick(val item: FeeItem) : FeesSummaryEvent
    data object OnHistoryClick : FeesSummaryEvent
    data object OnBreakdownClick : FeesSummaryEvent
}