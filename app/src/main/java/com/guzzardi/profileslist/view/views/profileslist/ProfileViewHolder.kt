package com.guzzardi.profileslist.view.views.profileslist

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.ProfileViewHolderBinding
import com.guzzardi.profileslist.model.UserProfile
import com.guzzardi.profileslist.view.fragments.ProfilesListFragmentDirections
import com.guzzardi.profileslist.view.utils.setImageUri

class ProfileViewHolder(binding: ProfileViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val foregroundView = binding.foregroundView
    val profileImage = binding.profileImage
    val fullName = binding.profileFullName

    private val fullNameStringTemplate =
        binding.root.context.getString(R.string.profile_view_holder_full_name_template)

    fun bind(data: UserProfile) {
        bindProfileImage(data.profileImageUri)
        fullName.text = fullNameStringTemplate.format(data.givenName, data.familyName)
        itemView.setOnClickListener {
            goToEditThisProfile()
        }
    }

    private fun bindProfileImage(imageUri: Uri?) {
        profileImage.setImageUri(itemView.context, imageUri)
    }

    private fun goToEditThisProfile() {
        val action = ProfilesListFragmentDirections.actionUserProfilesToEditProfile(adapterPosition)
        itemView.findNavController().navigate(action)

    }

    companion object {
        fun create(parent: ViewGroup): RecyclerView.ViewHolder {
            val binding = ProfileViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ProfileViewHolder(binding)
        }
    }
}
