package com.keelim.orange.domain.feed

import com.keelim.orange.data.repository.feed.FeedRepository

class CreateChallengeUseCase(
    private val feedRepository: FeedRepository,
) {
    suspend fun createChallenge(information:Any){
        feedRepository.createChallenge(information)
    }
}