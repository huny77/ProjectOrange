package com.keelim.orange.data.repository.season.create

import com.keelim.orange.data.model.season.Article
import java.io.File

interface CreateRepository {
  suspend fun upload(challengeId: Int, userId: Int, title: String, description: String): Article
  suspend fun image(articleId: Int, file: File)
}
