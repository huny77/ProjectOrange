package com.keelim.orange.ui.profile.favorite

import com.keelim.orange.data.model.entity.Favorite

sealed class FavoriteState {
  object UnInitialized : FavoriteState()
  object Loading : FavoriteState()
  object Error : FavoriteState()
  data class Success(
    val data: List<Favorite>
  ) : FavoriteState()
}
