package com.keelim.orange.domain.season

import com.keelim.orange.data.repository.season.other.OtherRepository

class OtherUseCase(
  private val otherRepository: OtherRepository,
) {
  suspend fun authenticate(): Boolean {
    return otherRepository.authenticate()
  }

  suspend fun like(): Boolean {
    return otherRepository.like()
  }

  suspend fun sue(): Boolean {
    return otherRepository.sue()
  }

  suspend fun share(): Boolean {
    return otherRepository.share()
  }
}
