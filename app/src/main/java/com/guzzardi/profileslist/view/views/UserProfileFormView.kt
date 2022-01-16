package com.guzzardi.profileslist.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.UserProfileFormViewBinding

class UserProfileFormView(context: Context, attrs: AttributeSet? = null): ConstraintLayout(context, attrs) {

    private var binding: UserProfileFormViewBinding =
        UserProfileFormViewBinding.inflate(LayoutInflater.from(context), this)

    val givenName: String
        get() = binding.givenNameInputText.text.toString()

    val lastName: String
        get() = binding.familyNameInputText.text.toString()

    val email: String
        get() = binding.emailNameInputText.text.toString()

    fun validateForm(): Boolean {
        val validGivenName = validateGivenName()
        val validLastName = validateFamilyName()
        val validEmail = validateEmail()
        return validGivenName && validLastName && validEmail
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
    private fun validateEmail(): Boolean {
        return if (binding.emailNameInputText.text.toString().isBlank()) {
            binding.emailNameInputLayout.error = context.getString(R.string.profile_email_name_error_message)
            false
        } else {
            binding.emailNameInputLayout.error = null
            true
        }
    }
}
