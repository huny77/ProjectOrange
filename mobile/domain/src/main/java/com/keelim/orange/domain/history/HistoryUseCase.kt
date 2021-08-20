package com.keelim.orange.domain.history

import com.keelim.orange.data.model.entity.History
import com.keelim.orange.data.repository.history.HistoryRepository

class HistoryUseCase(
  private val historyRepository: HistoryRepository,
) {
  suspend fun getListAll(): List<History> {
    return historyRepository.getAll()
  }

  suspend fun insert(history: History) {
    return historyRepository.insertHistory(history)
  }

  suspend fun delete(history: History) {
    return historyRepository.deleteHistory(history)
  }
}
