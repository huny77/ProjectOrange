package com.keelim.orange.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.data.response.DetailResponse
import com.keelim.orange.domain.auth.FavoriteUseCase
import com.keelim.orange.domain.feed.GetDetailInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val getDetailInformationUseCase: GetDetailInformationUseCase,
  private val favoriteUseCase: FavoriteUseCase,
) : ViewModel() {
  private val _state = MutableLiveData<DetailState>()
  val state: LiveData<DetailState> = _state

  fun fetchData(uid:Int) = viewModelScope.launch{
    setState(
      DetailState.UnInitialized
    )
    try {
      setState(
        DetailState.Success(
            getDetailInformationUseCase.invoke(uid)?: Search2(
              categoryId = -1,
              challengeDescribe = "",
              challengeId = -1,
              challengeTitle = "",
              currentMembers = -1,
              endDate = "",
              imagePath = "",
              managerId = -1,
              maxMembers = -1,
              minMembers = -1,
              periodId = -1,
              startDate = "",
              totalPoint = -1
            )
        )
      )
    } catch (e: Exception) {
      setState(
        DetailState.Error
      )
    }
  }

  fun favoriteAdd(favorite: Favorite) = viewModelScope.launch{
    favoriteUseCase.insert(favorite)
  }
  fun sign(challengeId:Int, userId:Int) = viewModelScope.launch {
    getDetailInformationUseCase.sign(challengeId, userId)
  }

  private fun setState(value: DetailState) {
    _state.value = value
  }
}
