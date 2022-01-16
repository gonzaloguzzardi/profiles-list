package com.guzzardi.profileslist.viewmodel

import android.net.Uri
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
    var selectedImage: Uri? = null

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

    fun getUserProfileAt(position: Int): UserProfile? =
        userProfilesState.value.userProfiles.getOrNull(position)

    fun createUserProfile(givenName: String, lastName: String, email: String, imageUri: Uri?) {
        val newUserProfile = UserProfile(givenName, lastName, email, imageUri)
        val newList = userProfilesState.value.userProfiles.toMutableList()
        newList.add(newUserProfile)
        userProfilesState.value = UserProfilesState(newList)
    }

    fun editUserProfile(index: Int, givenName: String, lastName: String, email: String, imageUri: Uri?) {
        if (index > userProfilesState.value.userProfiles.size) return

        val newUserProfile = UserProfile(givenName, lastName, email, imageUri)
        val newList = userProfilesState.value.userProfiles.toMutableList()
        newList[index] = newUserProfile
        userProfilesState.value = UserProfilesState(newList)
    }

    fun removeUserProfile(userProfile: UserProfile) {
        val newList = userProfilesState.value.userProfiles.toMutableList()
        newList.remove(userProfile)
        userProfilesState.value = UserProfilesState(newList)
    }
}
