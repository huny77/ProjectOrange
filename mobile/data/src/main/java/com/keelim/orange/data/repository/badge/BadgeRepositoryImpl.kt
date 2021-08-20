package com.keelim.orange.data.repository.badge

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.model.Badge
import com.keelim.orange.data.model.Badge2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BadgeRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory,
) : BadgeRepository {
  override suspend fun getAllBadge(): List<Badge> = withContext(dispatcher) {
    apiRequestFactory.retrofit.getAllBadgeList().body()?.mapNotNull {
      Badge(
        it.badgeTitle,
        it.badgeDescribe,
        it.badgeImagePath
      )
    } ?: listOf()
  }

  override suspend fun getMyBadge(userId: Int): List<Badge2> = withContext(dispatcher) {
    apiRequestFactory.retrofit.getMyBadge(userId).body()?.map {
      Badge2(
        it.badge,
        "",
        "",
        "",
      )
    } ?: emptyList()
  }
}
