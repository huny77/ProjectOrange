package com.keelim.orange.ui.season.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.season.SeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CommentViewModel @Inject constructor(
  private val seasonUseCase: SeasonUseCase,
) : ViewModel() {
  private val _state = MutableLiveData<CommentState>(CommentState.UnInitialized)
  val state: LiveData<CommentState> get() = _state

  fun fetchData(articleId: Int) = viewModelScope.launch {
    setState(
      CommentState.Loading
    )

    try {
      setState(
        CommentState.Success(
          seasonUseCase.getComments(articleId)
        )
      )
    } catch (e: Exception) {
      setState(
        CommentState.Error
      )
    }
  }

  fun writeComment(articleId:Int, userId:Int, message:String) = viewModelScope.launch {
    seasonUseCase.write(articleId, userId, message)
  }

  private fun setState(value: CommentState) {
    _state.value = value
  }
}
