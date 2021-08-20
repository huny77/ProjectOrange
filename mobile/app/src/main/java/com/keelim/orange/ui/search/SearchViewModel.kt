package com.keelim.orange.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.data.model.entity.History
import com.keelim.orange.data.repository.history.HistoryRepository
import com.keelim.orange.domain.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {
    private var _state  = MutableLiveData<HistoryState>(HistoryState.UnInitialized)
    val state:LiveData<HistoryState> get() = _state

    fun getAll() = viewModelScope.launch {
        setState(
            HistoryState.Loading
        )

        try {
            setState(
                HistoryState.Success(
                    historyRepository.getAll()
                )
            )
        } catch (e: Exception) {
            setState(
                HistoryState.Error
            )
            Timber.d("exception in the SearchViewModel")
        }
    }

    fun search(query: String) = viewModelScope.launch {
        setState(
            HistoryState.SearchSuccess(
                searchUseCase.search(query)
            )
        )
    }

    fun insertHistory(history: History) = viewModelScope.launch {
        historyRepository.insertHistory(history)
    }

    fun deleteHistory(history: History) = viewModelScope.launch {
        historyRepository.deleteHistory(history)
    }

    private fun setState(state: HistoryState) {
        _state.value = state
    }
}