package com.keelim.orange.domain.profile

import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.repository.profile.ProfileRepository

class GetIngChallengeUseCase(
  private val profileRepository: ProfileRepository
) {
  suspend operator fun invoke(userId:Int): List<Search2> {
    return profileRepository.getIngChallengeList(userId)
  }
}
