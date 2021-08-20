package com.keelim.orange.data.repository.favorite

import com.keelim.orange.data.db.AppDatabase
import com.keelim.orange.data.model.entity.Favorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoriteRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val db: AppDatabase,
) : FavoriteRepository {
  override suspend fun getAll(): List<Favorite> = withContext(dispatcher) {
    db.favoriteDao().getAll()
  }

  override suspend fun insertFavorite(favorite: Favorite) = withContext(dispatcher) {
    db.favoriteDao().insertFavorite(favorite)
  }

  override suspend fun deleteFavorite(favorite: Favorite) = withContext(dispatcher) {
    db.favoriteDao().deleteFavorite(favorite)
  }
}
