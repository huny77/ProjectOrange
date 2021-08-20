package com.keelim.orange.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keelim.orange.data.model.entity.Favorite

@Dao
interface FavoriteDao {
  @Query("SELECT * FROM favorite")
  suspend fun getAll(): List<Favorite>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavorite(favorite: Favorite)

  @Delete
  suspend fun deleteFavorite(favorite: Favorite)
}
