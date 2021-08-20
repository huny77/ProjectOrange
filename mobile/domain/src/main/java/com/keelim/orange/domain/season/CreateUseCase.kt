package com.keelim.orange.domain.season

import com.keelim.orange.data.repository.season.create.CreateRepository
import timber.log.Timber
import java.io.File

class CreateUseCase(
  private val createRepository: CreateRepository,
) {
  suspend fun upload(
    challengeId: Int,
    userId: Int,
    title: String,
    description: String,
    file: File,
  ) {
    //1.
    try {
      val article = createRepository.upload(challengeId, userId, title, description)

      val imageUpload = createRepository.image(article.articleId, file)
    } catch (e:Exception){
      Timber.e("[what the] $e")
    }
  }
}
