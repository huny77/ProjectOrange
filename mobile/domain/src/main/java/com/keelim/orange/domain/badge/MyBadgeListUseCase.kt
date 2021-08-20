package com.keelim.orange.domain.badge

import com.keelim.orange.data.model.Badge
import com.keelim.orange.data.model.Badge2
import com.keelim.orange.data.repository.badge.BadgeRepository

class MyBadgeListUseCase(
  private val badgeRepository: BadgeRepository
) {
  suspend operator fun invoke(userId: Int): List<Badge2> {
    return badgeRepository.getMyBadge(userId)
  }
}
