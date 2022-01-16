package com.guzzardi.profileslist.view.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.UserProfileFormViewBinding
import com.guzzardi.profileslist.view.utils.setImageUri

class UserProfileFormView(context: Context, attrs: AttributeSet? = null): ConstraintLayout(context, attrs) {

    private var binding: UserProfileFormViewBinding =
        UserProfileFormViewBinding.inflate(LayoutInflater.from(context), this)

    val givenName: String
        get() = binding.givenNameInputText.text.toString()

    val lastName: String
        get() = binding.familyNameInputText.text.toString()

    val bio: String
        get() = binding.bioInputText.text.toString()

    val profileImage: ImageView
        get() = binding.profileFormImage

    fun validateForm(): Boolean {
        val validGivenName = validateGivenName()
        val validLastName = validateFamilyName()
        val validBio = validateBio()
        return validGivenName && validLastName && validBio
    }

    fun setTextFields(givenName: String, familyName: String, bio: String) {
        binding.givenNameInputText.setText(givenName)
        binding.familyNameInputText.setText(familyName)
        binding.bioInputText.setText(bio)
    }

    fun setImage(imageUri: Uri?) {
        binding.profileFormImage.setImageUri(context, imageUri)
    }

    private fun validateGivenName(): Boolean {
        return if (binding.givenNameInputText.text.toString().isBlank()) {
            binding.givenNameInputLayout.error = context.getString(R.string.profile_given_name_error_message)
            false
        } else {
            binding.givenNameInputLayout.error = null
            true
        }
    }

    private fun validateFamilyName(): Boolean {
        return if (binding.familyNameInputText.text.toString().isBlank()) {
            binding.familyNameInputLayout.error = context.getString(R.string.profile_family_name_error_message)
            false
        } else {
            binding.familyNameInputLayout.error = null
            true
        }
    }

    private fun validateBio(): Boolean {
        return if (binding.bioInputText.text.toString().isBlank()) {
            binding.bioInputLayout.error = context.getString(R.string.profile_bio_error_message)
            false
        } else {
            binding.bioInputLayout.error = null
            true
        }
    }
}
