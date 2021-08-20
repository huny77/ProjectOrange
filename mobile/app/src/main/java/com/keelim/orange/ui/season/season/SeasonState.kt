package com.keelim.orange.ui.season.season

import com.keelim.orange.data.model.season.Article

sealed class SeasonState {
  object UnInitialized : SeasonState()
  object Loading : SeasonState()
  data class Success(
    val data: List<Article>
  ) : SeasonState()
  object Error : SeasonState()
}
