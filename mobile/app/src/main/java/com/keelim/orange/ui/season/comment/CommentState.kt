package com.keelim.orange.ui.season.comment

import com.keelim.orange.data.model.season.Comment


sealed class CommentState {
  object UnInitialized : CommentState()
  object Loading : CommentState()
  object Error : CommentState()
  data class Success(
    val data: List<Comment>,
  ) : CommentState()

}
