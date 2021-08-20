package com.keelim.orange.ui.season.sheet.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.season.OtherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OtherViewModel @Inject constructor(
    private val otherUseCase: OtherUseCase,
) : ViewModel() {

    fun authenticate() = viewModelScope.launch {
        otherUseCase.authenticate()
    }

    fun sue() = viewModelScope.launch {
        otherUseCase.sue()
    }

    fun share() = viewModelScope.launch {
        otherUseCase.share()
    }

    fun like() = viewModelScope.launch {
        otherUseCase.like()
    }
}