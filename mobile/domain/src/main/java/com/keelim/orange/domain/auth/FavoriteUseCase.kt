package com.keelim.orange.domain.auth

import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.repository.favorite.FavoriteRepository

class FavoriteUseCase(
  private val favoriteRepository: FavoriteRepository,
) {
  suspend fun getListAll(): List<Favorite> {
    return favoriteRepository.getAll()
  }

  suspend fun insert(favorite: Favorite) {
    return favoriteRepository.insertFavorite(favorite)
  }

  suspend fun delete(favorite: Favorite) {
    return favoriteRepository.deleteFavorite(favorite)
  }
}
