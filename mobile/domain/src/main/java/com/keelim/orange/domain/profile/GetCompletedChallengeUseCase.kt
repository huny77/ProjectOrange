package com.keelim.orange.domain.profile

import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.repository.profile.ProfileRepository

class GetCompletedChallengeUseCase(
  private val profileRepository: ProfileRepository
) {
  suspend operator fun invoke(): List<Favorite> {
    return profileRepository.getCompletedChallengeList()
  }
}
