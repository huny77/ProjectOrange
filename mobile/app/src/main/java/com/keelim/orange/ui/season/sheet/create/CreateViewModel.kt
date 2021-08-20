package com.keelim.orange.ui.season.sheet.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keelim.orange.domain.season.CreateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import java.io.File

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val createUseCase: CreateUseCase
): ViewModel() {

    fun upload(challengeId: Int, userId: Int, title: String, description: String, file: File) =
        viewModelScope.launch {
            createUseCase.upload(challengeId, userId, title, description, file)
        }
}