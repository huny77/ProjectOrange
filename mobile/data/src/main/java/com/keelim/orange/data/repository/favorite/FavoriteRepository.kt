package com.keelim.orange.data.repository.favorite

import com.keelim.orange.data.model.entity.Favorite

interface FavoriteRepository {
  suspend fun getAll(): List<Favorite>
  suspend fun insertFavorite(favorite: Favorite)
  suspend fun deleteFavorite(favorite: Favorite)
}
