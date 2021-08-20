package com.keelim.orange.ui.season.ranking

import com.keelim.orange.data.model.ranking.Ranking


sealed class RankingState {
  object UnInitialized : RankingState()
  object Loading : RankingState()
  object Error : RankingState()
  data class Success1(
    val data: List<Ranking>
  ) : RankingState()

  data class Success2(
    val data:List<Ranking>
  ):RankingState()
}
