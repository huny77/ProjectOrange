package com.keelim.orange.ui.profile.badge

import com.keelim.orange.data.model.Badge
import com.keelim.orange.data.model.Badge2

sealed class BadgeState {
  object UnInitialized : BadgeState()
  object Loading : BadgeState()
  data class Success(
    val data: List<Badge>,
    val data2: List<Badge2>,
  ) : BadgeState()
  object Error : BadgeState()
}
