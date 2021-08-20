package com.keelim.orange.data.repository.fight

import com.keelim.orange.data.model.Search2

interface FightRepository {
    suspend fun getBattleInformation(challengeId:Int):Search2?
}