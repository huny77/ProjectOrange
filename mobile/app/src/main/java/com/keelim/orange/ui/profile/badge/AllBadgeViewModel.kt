package com.keelim.orange.ui.profile.badge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.badge.GetAllBadgeListUseCase
import com.keelim.orange.domain.badge.MyBadgeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class AllBadgeViewModel @Inject constructor(
    private val getAllBadgeListUseCase: GetAllBadgeListUseCase,
    private val myBadgeListUseCase: MyBadgeListUseCase,
): ViewModel() {
    private var _state = MutableLiveData<BadgeState>(BadgeState.UnInitialized)
    val state: LiveData<BadgeState> get() = _state

    fun fetchData(userId: Int) = viewModelScope.launch {
        setState(
            BadgeState.Loading
        )

        try {
            val data1 = getAllBadgeListUseCase.invoke()
            val data2 = myBadgeListUseCase.invoke(userId)
            setState(
                BadgeState.Success(
                    data1,
                    data2,
                )
            )
        } catch (e: Exception) {
            Timber.e(e)
            setState(
                BadgeState.Error
            )
        }
    }

    private fun setState(state: BadgeState) {
        _state.value = state
    }
}