package com.keelim.orange.ui.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.fight.OpponentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FightViewModel @Inject constructor(
  private val opponentUseCase: OpponentUseCase,
) : ViewModel() {
  private val _state = MutableLiveData<FightState>()
  val state: LiveData<FightState> = _state

  fun fetchData(challengeId: Int) = viewModelScope.launch {
    setState(
      FightState.UnInitialized
    )
    try {
      val (data1, data2) = opponentUseCase.getBattleInformation(challengeId)
      setState(
        FightState.Success(
          data1,
          data2
        )
      )
    } catch (e: Exception) {
      setState(
        FightState.Error
      )
    }
  }

  private fun setState(value: FightState) {
    _state.value = value
  }
}
