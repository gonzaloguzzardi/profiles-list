package com.guzzardi.profileslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guzzardi.profileslist.model.UserProfile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfilesViewModel(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    val userProfilesState: MutableStateFlow<UserProfilesState> =
        MutableStateFlow(UserProfilesState(emptyList()))

    fun fetchProfiles() {
        viewModelScope.launch(ioDispatcher) {
            val newState = UserProfilesState(
                listOf(
                    UserProfile("Gonzalo", "Guzzardi", null),
                    UserProfile("Maria Paula", "Palilon", null)
                )
            )
            userProfilesState.value = newState
        }
    }
}
