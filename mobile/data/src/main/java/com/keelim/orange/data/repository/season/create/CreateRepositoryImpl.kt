package com.keelim.orange.data.repository.season.create

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.call.CreateCall
import com.keelim.orange.data.model.season.Article
import com.keelim.orange.util.FormDataUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File

class CreateRepositoryImpl(
  private val dispatcher: CoroutineDispatcher,
  private val apiRequestFactory: ApiRequestFactory
) : CreateRepository {
  private val replace = Article(
    "",
    -1,
    "",
    -1,
    "",
    -1,
    false,
    ""
  )

  override suspend fun upload(
    challengeId: Int,
    userId: Int,
    title: String,
    description: String,
  ): Article = withContext(dispatcher) {
    val response = apiRequestFactory.retrofit.upload(
      CreateCall(
        description,
        challengeId,
        title,
        userId,
        false
      )
    )
    if (response.isSuccessful) {
      return@withContext response.body()?.let {
        Article(
          articleContent = it.articleContent,
          articleId = it.articleId,
          articleWritetime = it.articleWritetime,
          challenge = it.challenge,
          title = it.title,
          user = it.user,
          verified = it.verified,
          articleImage = ""
        )
      } ?: replace
    } else {
      return@withContext replace
    }
  }

  override suspend fun image(articleId: Int, file: File): Unit = withContext(dispatcher) {
    val id = FormDataUtil.getBody("articleId", articleId)
    val image = FormDataUtil.getImageBody("image", file)

    apiRequestFactory.retrofit.imageUpload(id, image)
  }


}
