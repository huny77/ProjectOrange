package com.keelim.orange.data.repository.season

import com.keelim.orange.data.api.ApiRequestFactory
import com.keelim.orange.data.call.WriteComment
import com.keelim.orange.data.model.season.Article
import com.keelim.orange.data.model.season.Comment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SeasonRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val apiRequestFactory: ApiRequestFactory,
) : SeasonRepository {
    override suspend fun article(challenge: Int): List<Article> = withContext(dispatcher) {
        val response = apiRequestFactory.retrofit.article(challenge)
        if (response.isSuccessful) {
            return@withContext response.body()?.map {
                Article(
                    it.articleContent,
                    it.articleId,
                    it.articleWritetime,
                    it.challenge,
                    it.title,
                    it.user,
                    it.verified,
                    "",
                )
            } ?: emptyList()
        } else {
            return@withContext emptyList()
        }
    }

    override suspend fun image(article: Int): String? = withContext(dispatcher) {
        val response = apiRequestFactory.retrofit.image(article)
        return@withContext if (response.isSuccessful) {
            response.body()?.get(0)
        } else {
            null
        }
    }

    override suspend fun comments(articleId: Int): List<Comment> = withContext(dispatcher) {
        val response = apiRequestFactory.retrofit.comments(articleId)
        if (response.isSuccessful) {
            return@withContext response.body()?.mapNotNull {
                Comment(
                    it.user.toString(),
                    it.commentContent,
                )
            } ?: emptyList()
        } else {
            return@withContext emptyList()
        }
    }

    override suspend fun writeComment(articleId: Int, userId: Int, message: String):Unit = withContext(dispatcher) {
        apiRequestFactory.retrofit.writeComment(
            WriteComment(
                articleId,
                message,
                userId
            )
        )
    }
}
