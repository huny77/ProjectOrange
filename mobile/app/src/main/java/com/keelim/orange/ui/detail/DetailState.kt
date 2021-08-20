package com.keelim.orange.ui.detail

import com.keelim.orange.data.model.Search2

sealed class DetailState {
  object UnInitialized : DetailState()
  object Loading : DetailState()
  data class Success(
    val data: Search2,
  ) : DetailState()
  object Error : DetailState()
}
