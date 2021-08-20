package com.keelim.orange.domain.feed

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.repository.feed.FeedRepository

class GetDetailInformationUseCase(
  private val detailRepository: FeedRepository
) {
  suspend operator fun invoke(uid: Int): Search2? {
    return detailRepository.getDetail(uid)
  }

  suspend fun sign(challengeId: Int, userId: Int) {
    return detailRepository.sign(challengeId, userId)
  }
}
