package com.keelim.orange.data.repository.season

import com.keelim.orange.data.model.season.Article
import com.keelim.orange.data.model.season.Comment

interface SeasonRepository {
    suspend fun article(challenge:Int):List<Article>
    suspend fun image(article: Int): String?
    suspend fun comments(articleId: Int): List<Comment>
    suspend fun writeComment(articleId: Int, userId: Int, message: String): Unit
}