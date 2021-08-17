package com.keelim.orange.data.repository.season

import com.keelim.orange.data.model.ranking.Ranking

interface RankingRepository {
  suspend fun point(): List<Ranking>
  suspend fun start(): List<Ranking>
}
