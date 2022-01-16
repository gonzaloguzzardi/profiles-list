package com.guzzardi.profileslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guzzardi.profileslist.model.UserProfile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserProfilesViewModel(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    val userProfilesState: MutableStateFlow<UserProfilesState> =
        MutableStateFlow(UserProfilesState(emptyList()))

    fun fetchProfiles() {
        if (userProfilesState.value.userProfiles.isNotEmpty()) return

        // Mock some initial data
        viewModelScope.launch(ioDispatcher) {
            val newState = UserProfilesState(
                listOf(
                    UserProfile("Gonzalo", "Guzzardi", "gg@gmail.com", null),
                    UserProfile("Maria Paula", "Palilon", "mp@gmail.com", null)
                )
            )
            userProfilesState.value = newState
        }
    }

    fun createUserProfile(givenName: String, lastName: String, email: String) {
        val newUserProfile = UserProfile(givenName, lastName, email, null)
        val newList = userProfilesState.value.userProfiles.toMutableList()
        newList.add(newUserProfile)
        userProfilesState.value = UserProfilesState(newList)
    }
}
