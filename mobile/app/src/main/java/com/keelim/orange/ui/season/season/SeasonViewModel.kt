package com.keelim.orange.ui.season.season

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.season.SeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val seasonUseCase: SeasonUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<SeasonState>()
    val state: LiveData<SeasonState> = _state

    fun fetchData(challengeId: Int) = viewModelScope.launch {
        setState(
            SeasonState.UnInitialized
        )
        try {
            setState(
                SeasonState.Success(
                    seasonUseCase.articleFeed(challengeId)
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
            setState(
                SeasonState.Error
            )
        }
    }

    private fun setState(value: SeasonState) {
        _state.value = value
    }
}
