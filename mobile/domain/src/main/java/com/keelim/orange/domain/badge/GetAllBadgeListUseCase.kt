package com.keelim.orange.domain.badge

import com.keelim.orange.data.model.Badge
import com.keelim.orange.data.repository.badge.BadgeRepository

class GetAllBadgeListUseCase(
  private val badgeRepository: BadgeRepository
) {
  suspend operator fun invoke(): List<Badge> {
    return badgeRepository.getAllBadge()
  }
}
