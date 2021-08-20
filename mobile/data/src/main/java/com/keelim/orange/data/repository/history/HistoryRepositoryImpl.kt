package com.keelim.orange.data.repository.history

import com.keelim.orange.data.db.AppDatabase
import com.keelim.orange.data.model.entity.History
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class HistoryRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val db: AppDatabase
) : HistoryRepository {
  override suspend fun getAll(): List<History> = withContext(dispatcher) {
    db.historyDao().getAll()
  }

  override suspend fun insertHistory(history: History) = withContext(dispatcher) {
    db.historyDao().insertHistory(history)
  }

  override suspend fun deleteHistory(history: History) = withContext(dispatcher) {
    db.historyDao().deleteHistory(history)
  }
}
