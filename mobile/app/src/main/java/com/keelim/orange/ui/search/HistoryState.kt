package com.keelim.orange.ui.search

import com.keelim.orange.data.model.Search
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.History

sealed class HistoryState {
    object UnInitialized : HistoryState()
    object Loading : HistoryState()
    data class Success(
        val data: List<History>,
    ) : HistoryState()

    object Error : HistoryState()
    data class SearchSuccess(
        val data: List<Search2>,
    ) : HistoryState()
}