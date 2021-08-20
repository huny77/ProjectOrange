package com.keelim.orange.domain.season

import com.keelim.orange.data.model.season.Article
import com.keelim.orange.data.model.season.Comment
import com.keelim.orange.data.repository.season.SeasonRepository

class SeasonUseCase(
    private val seasonRepository: SeasonRepository,
) {
    suspend fun articleFeed(challenge: Int): List<Article> {
        val result = seasonRepository.article(challenge)
        return result.map {
            Article(
                it.articleContent,
                it.articleId,
                it.articleWritetime,
                it.challenge,
                it.title,
                it.user,
                it.verified,
                seasonRepository.image(it.articleId) ?: ""
            )
        }
    }

    suspend fun getComments(articleId:Int): List<Comment>{
        return seasonRepository.comments(articleId)
    }

    suspend fun write(articleId:Int, userId:Int, message:String){
        return seasonRepository.writeComment(articleId, userId, message)
    }


}