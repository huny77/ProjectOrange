package com.keelim.orange.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.feed.CategoryUseCase
import com.keelim.orange.domain.feed.ChallengeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FeedViewModel @Inject constructor(
  private val categoryUseCase: CategoryUseCase,
  private val challengeListUseCase: ChallengeListUseCase,
) : ViewModel() {
  private val _state = MutableLiveData<FeedState>(FeedState.UnInitialized)
  val state: LiveData<FeedState> get() = _state

  fun fetchData() = viewModelScope.launch {
    setState(
      FeedState.Loading
    )

    setState(
      FeedState.Success(
        categoryUseCase.invoke(),
        challengeListUseCase.invoke(),
      )
    )
  }

  private fun setState(value: FeedState) {
    _state.value = value
  }
}
