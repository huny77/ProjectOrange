package com.keelim.orange.ui.profile.profile

import com.keelim.orange.data.model.Profile
import com.keelim.orange.data.model.Search2
import com.keelim.orange.data.model.entity.Favorite

sealed class ProfileState {
    object UnInitialized : ProfileState()
    object Loading : ProfileState()
    data class Success(
        val ing: List<Search2>,
        val completed: List<Search2>,
    ) : ProfileState()

    data class ProfileSuccess(
        val data: Profile,
    ) : ProfileState()

    object Error : ProfileState()

}
