package com.keelim.orange.data.repository.profile

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.Profile
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) : ProfileRepository {
  val sample = Profile(
    "",
    "",
    "",
    "",
  )

  override suspend fun getIngChallengeList(userId: Int): List<Search2> = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.getIngList(userId)
    if (response.isSuccessful) {
      return@withContext response.body()?.map {
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
      } ?: emptyList()
    } else {
      return@withContext emptyList()
    }
  }

  override suspend fun getCompletedChallengeList(): List<Favorite> = withContext(dispatcher) {
    return@withContext apiRequestFactory.retrofit.getCompletedList()
  }

  override suspend fun getProfileInformation(userId: Int): Profile = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.profile(userId)
    if (response.isSuccessful) {
      if (response.body() == null) {
        return@withContext sample
      } else{
        return@withContext Profile(
          response.body()!!.data1.userId.toString(),
          response.body()!!.data1.nickname.toString(),
          response.body()!!.data1.email.toString(),
          response.body()!!.data1.profileImagePath.toString(),
        )
      }
    }
    return@withContext Profile(
      "",
      "",
      "",
      "",
    )
  }
}
