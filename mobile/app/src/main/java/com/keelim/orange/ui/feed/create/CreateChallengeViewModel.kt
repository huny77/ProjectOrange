package com.keelim.orange.ui.feed.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.feed.CreateChallengeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CreateChallengeViewModel @Inject constructor(
    private val createChallengeUseCases: CreateChallengeUseCase,
) : ViewModel() {
    fun createChallenge(information: Any) = viewModelScope.launch {

    }
}