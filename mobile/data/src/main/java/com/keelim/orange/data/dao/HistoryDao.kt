package com.keelim.orange.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keelim.orange.data.model.entity.History

@Dao
interface HistoryDao {
  @Query("SELECT * FROM history")
  suspend fun getAll(): List<History>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertHistory(history: History)

  @Delete
  suspend fun deleteHistory(history: History)
}
