package com.guzzardi.profileslist.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.FragmentEditProfileBinding
import com.guzzardi.profileslist.model.UserProfile
import com.guzzardi.profileslist.view.utils.CHOOSE_FROM_GALLERY_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.TAKE_PHOTO_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.getCameraPhotoImageUri
import com.guzzardi.profileslist.view.utils.openPhotoPickerDialog
import com.guzzardi.profileslist.view.utils.setActionBarTitle
import com.guzzardi.profileslist.view.utils.setImageUri
import com.guzzardi.profileslist.viewmodel.UserProfilesViewModel

class EditProfileFragment : Fragment() {

    private val userProfilesViewModel: UserProfilesViewModel by activityViewModels()
    private val args: EditProfileFragmentArgs by navArgs()

    private var binding: FragmentEditProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userProfilesViewModel.selectedImage = null
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.fragment_edit_profile_title))
        setupSaveChangesButtonListener()
        setupProfileImageListener()
        loadUserProfileData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data ?: getCameraPhotoImageUri(context)
                userProfilesViewModel.selectedImage = selectedImage
                updateProfileImage(selectedImage)
            }
            CHOOSE_FROM_GALLERY_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data
                userProfilesViewModel.selectedImage = selectedImage
                updateProfileImage(selectedImage)
            }
        }
    }

    private fun loadUserProfileData() {
        userProfilesViewModel.getUserProfileAt(args.profileIndex)?.let { userProfile ->
            binding?.run {
                profileFormView.setTextFields(userProfile.givenName, userProfile.familyName, userProfile.email)
                profileFormView.setImage(userProfile.profileImageUri)
            }
        }
    }

    private fun setupSaveChangesButtonListener() {
        binding?.run {
            profileCreateButton.setOnClickListener {
                if (profileFormView.validateForm()) {
                    userProfilesViewModel.editUserProfile(
                        args.profileIndex,
                        profileFormView.givenName,
                        profileFormView.lastName,
                        profileFormView.email,
                        userProfilesViewModel.selectedImage
                    )
                    findNavController().popBackStack()
                }

            }
        }
    }

    private fun setupProfileImageListener() {
        binding?.run {
            profileFormView.profileImage.setOnClickListener {
                openPhotoPickerDialog()
            }
        }
    }

    private fun updateProfileImage(imageUri: Uri?) {
        binding?.run {
            context?.let { context ->
                profileFormView.profileImage.setImageUri(context, imageUri)
            }
        }
    }
}
