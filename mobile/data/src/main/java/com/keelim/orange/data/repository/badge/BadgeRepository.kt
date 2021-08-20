package com.keelim.orange.data.repository.badge

import com.keelim.orange.data.model.Badge
import com.keelim.orange.data.model.Badge2

interface BadgeRepository {
  suspend fun getAllBadge(): List<Badge>
  suspend fun getMyBadge(userId: Int): List<Badge2>
}
