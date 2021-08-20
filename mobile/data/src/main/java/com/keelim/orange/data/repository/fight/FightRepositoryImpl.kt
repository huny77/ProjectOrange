package com.keelim.orange.data.repository.fight

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.Search2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FightRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val apiRequestFactory: ApiRequestFactory,
) : FightRepository {
    override suspend fun getBattleInformation(challengeId: Int):Search2? = withContext(dispatcher){
        val response = apiRequestFactory.retrofit.getOpponent(challengeId)
        if (response.isSuccessful) {
            return@withContext response.body()?.let {
                Search2(
                    it.categoryId,
                    it.challengeDescribe,
                    it.challengeId,
                    it.challengeTitle,
                    it.currentMembers,
                    it.endDate,
                    it.imagePath,
                    it.managerId,
                    it.maxMembers,
                    it.minMembers,
                    it.periodId,
                    it.startDate,
                    it.totalPoint
                )
            }
        } else {
            return@withContext null
        }
    }
}