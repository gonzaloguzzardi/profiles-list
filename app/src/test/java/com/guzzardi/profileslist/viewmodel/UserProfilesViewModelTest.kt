package com.guzzardi.profileslist.viewmodel

import android.net.Uri
import com.guzzardi.profileslist.RobolectricTest
import com.guzzardi.profileslist.model.UserProfile
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserProfilesViewModelTest : RobolectricTest() {

    private lateinit var viewModel: UserProfilesViewModel

    private val testProfile = UserProfile("Gonzalo", "Guzzardi", "gg@gmail.com", null)
    private val testProfile2 = UserProfile("Maria Paula", "Palilon", "mp@gmail.com", null)
    private val testList = listOf(testProfile, testProfile2)

    @Before
    fun setup() {
        viewModel = UserProfilesViewModel(Dispatchers.Unconfined)
    }

    @Test
    fun `fetchProfiles return empty list if viewModel already has loaded values`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState


        viewModel.fetchProfiles()

        Assert.assertEquals(testList, viewModel.userProfilesState.value.userProfiles)
    }

    @Test
    fun `fetchProfiles should return an initial list of profiles when it has no loaded values`() {
        Assert.assertTrue(viewModel.userProfilesState.value.userProfiles.isEmpty())

        viewModel.fetchProfiles()

        Assert.assertFalse(viewModel.userProfilesState.value.userProfiles.isEmpty())
    }

    @Test
    fun `getUserProfileAt should return user profile at given position`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState

        val retrievedProfile = viewModel.getUserProfileAt(1)

        Assert.assertEquals(testProfile2, retrievedProfile)
    }

    @Test
    fun `getUserProfileAt should return null if position is out of range`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState

        val retrievedProfile = viewModel.getUserProfileAt(100)

        Assert.assertNull(retrievedProfile)
    }

    @Test
    fun `createUserProfile should create and add a new profile to the profiles list`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState

        val name = "Test User"
        viewModel.createUserProfile(name, "", "", null)

        Assert.assertEquals(testList.size + 1, viewModel.userProfilesState.value.userProfiles.size)
        Assert.assertEquals(name, viewModel.getUserProfileAt(testList.size)?.givenName)
    }

    @Test
    fun `editUserProfile should edit profile at given position`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState

        val newName = "Test name"
        val newFamily = "Test family name"
        val newEmail = "Test email"
        val imageUri = Uri.parse("Test Uri")

        viewModel.editUserProfile(1, newName, newFamily, newEmail, imageUri)

        val editedProfile = viewModel.getUserProfileAt(1)
        Assert.assertEquals(editedProfile?.givenName, newName)
        Assert.assertEquals(editedProfile?.familyName, newFamily)
        Assert.assertEquals(editedProfile?.email, newEmail)
        Assert.assertEquals(editedProfile?.profileImageUri, imageUri)
    }

    @Test
    fun `removeUserProfile should remove user profile`() {
        val initialState = UserProfilesState(testList)
        viewModel.userProfilesState.value = initialState
        Assert.assertTrue(testList.contains(testProfile))

        viewModel.removeUserProfile(testProfile)

        Assert.assertFalse(viewModel.userProfilesState.value.userProfiles.contains(testProfile))
        Assert.assertEquals(testList.size - 1, viewModel.userProfilesState.value.userProfiles.size)
    }
}
