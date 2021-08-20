package com.keelim.orange.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.keelim.orange.data.dao.FavoriteDao
import com.keelim.orange.data.dao.HistoryDao
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.model.entity.History

@Database(
  version = 2,
  entities = [History::class, Favorite::class],
  autoMigrations = [
    AutoMigration(from = 1, to = 2)
  ]
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun historyDao(): HistoryDao
  abstract fun favoriteDao(): FavoriteDao
}
