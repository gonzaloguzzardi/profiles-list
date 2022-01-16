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
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.FragmentCreateProfileBinding
import com.guzzardi.profileslist.view.utils.CHOOSE_FROM_GALLERY_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.TAKE_PHOTO_REQUEST_CODE
import com.guzzardi.profileslist.view.utils.openPhotoPickerDialog
import com.guzzardi.profileslist.view.utils.setActionBarTitle
import com.guzzardi.profileslist.view.utils.setImageUri
import com.guzzardi.profileslist.viewmodel.UserProfilesViewModel

class CreateProfileFragment : Fragment() {

    private val userProfilesViewModel: UserProfilesViewModel by activityViewModels()

    private var binding: FragmentCreateProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userProfilesViewModel.selectedImage = null
        binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle(getString(R.string.fragment_create_profile_title))
        setupCreateButtonListener()
        setupProfileImageListener()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PHOTO_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data
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

    private fun setupCreateButtonListener() {
        binding?.run {
            profileCreateButton.setOnClickListener {
                if (profileFormView.validateForm()) {
                    userProfilesViewModel.createUserProfile(
                        profileFormView.givenName,
                        profileFormView.lastName,
                        profileFormView.bio,
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
