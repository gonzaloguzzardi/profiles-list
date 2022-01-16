package com.guzzardi.profileslist.view.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.FragmentProfilesListBinding
import com.guzzardi.profileslist.model.UserProfile
import com.guzzardi.profileslist.view.utils.CAMERA_PERMISSION_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.CHOOSE_FROM_GALLERY_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.setActionBarTitle
import com.guzzardi.profileslist.view.views.profileslist.ProfilesRecyclerView
import com.guzzardi.profileslist.viewmodel.UserProfilesState
import com.guzzardi.profileslist.viewmodel.UserProfilesViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ProfilesListFragment : Fragment(), ProfilesRecyclerView.OnProfileRemovedListener {

    private val userProfilesViewModel: UserProfilesViewModel by activityViewModels()

    private var binding: FragmentProfilesListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.fragment_profiles_list_title))
        listenToUiUpdates()
        bindCreateUserButton()
        listenToUiUpdates()
        userProfilesViewModel.fetchProfiles()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onRemoved(userProfile: UserProfile) {
        userProfilesViewModel.removeUserProfile(userProfile)
    }

    private fun listenToUiUpdates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                userProfilesViewModel.userProfilesState.collect { userProfileState ->
                    renderUiState(userProfileState)
                }
            }
        }
    }

    private fun bindCreateUserButton() {
        binding?.run {
            createUserButton.setOnClickListener {
                val action = ProfilesListFragmentDirections.actionUserProfilesToCreateProfile()
                findNavController().navigate(action)
            }
        }
    }

    private fun renderUiState(userProfiles: UserProfilesState) {
        binding?.run {
            profilesRecyclerView.setData(userProfiles.userProfiles, this@ProfilesListFragment)
        }
    }
}
