package com.keelim.orange.domain.feed

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.repository.feed.FeedRepository
import com.keelim.orange.data.response.feed.ChallengeResponse

class ChallengeListUseCase(
  private val feedRepository: FeedRepository,
) {
  suspend operator fun invoke(): List<Search2> {
    return feedRepository.challengeList()
  }
}
