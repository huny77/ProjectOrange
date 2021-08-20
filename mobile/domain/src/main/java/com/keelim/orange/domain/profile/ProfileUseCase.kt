package com.keelim.orange.domain.profile

import com.keelim.orange.data.model.Profile
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.repository.profile.ProfileRepository

class ProfileUseCase(
  private val profileRepository: ProfileRepository
) {
  suspend operator fun invoke(userId:Int): Profile {
    return profileRepository.getProfileInformation(userId)
  }
}
