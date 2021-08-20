package com.keelim.orange.domain.fight

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.repository.feed.FeedRepository
import com.keelim.orange.data.repository.fight.FightRepository

class OpponentUseCase(
    private val fightRepository: FightRepository,
    private val detailRepository: FeedRepository,
) {
    suspend fun getBattleInformation(challengeId: Int): Pair<Search2, Search2> {
        val challenge = detailRepository.getDetail(challengeId)
        val other = fightRepository.getBattleInformation(challengeId)
        return challenge!! to other!!
    }
}