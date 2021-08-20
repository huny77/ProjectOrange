package com.keelim.orange.data.repository.profile

import com.keelim.orange.data.model.Profile
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite

interface ProfileRepository {
  suspend fun getIngChallengeList(userId: Int): List<Search2>
  suspend fun getCompletedChallengeList(): List<Favorite>
  suspend fun getProfileInformation(userId: Int): Profile
}
