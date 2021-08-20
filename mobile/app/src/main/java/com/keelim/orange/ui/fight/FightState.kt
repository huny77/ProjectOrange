package com.keelim.orange.ui.fight

import com.keelim.orange.data.model.Search2

sealed class FightState {
  object UnInitialized : FightState()
  object Loading : FightState()
  data class Success(
    val you: Search2,
    val other:Search2
  ) : FightState()
  object Error : FightState()
}
