package com.keelim.orange.ui.noti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.FriendsOkUseCase
import com.keelim.orange.domain.NotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
  private val notificationUseCase: NotificationUseCase,
  private val friendsOkUseCase: FriendsOkUseCase,
) : ViewModel() {
  private var _state = MutableLiveData<NotificationState>(NotificationState.UnInitialized)
  val state: LiveData<NotificationState> get() = _state

  fun fetchData() = viewModelScope.launch {
    setState(
      NotificationState.Loading
    )

    try {
      setState(
        NotificationState.Success(
//          notificationUseCase.invoke()
        emptyList()
        )
      )
    } catch (e: Exception) {
      setState(
        NotificationState.Error
      )
    }
  }

  fun friendsOk() = viewModelScope.launch {
//    friendsOkUseCase.invoke()
  }

  private fun setState(state: NotificationState) {
    _state.value = state
  }
}
