package com.guzzardi.profileslist.view.views.profileslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guzzardi.profileslist.R
import com.guzzardi.profileslist.databinding.ProfileViewHolderBinding
import com.guzzardi.profileslist.model.ImageData
import com.guzzardi.profileslist.model.UserProfile

class ProfileViewHolder(val binding: ProfileViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val foregroundView = binding.foregroundView
    val profileImage = binding.profileImage
    val fullName = binding.profileFullName


    private val fullNameStringTemplate =
        binding.root.context.getString(R.string.profile_view_holder_full_name_template)

    fun bind(data: UserProfile) {
        fullName.text = fullNameStringTemplate.format(data.givenName, data.familyName)
        bindProfileImage(data.profileImagePath)
    }

    private fun bindProfileImage(imageData: ImageData?) {
        if (imageData == null) {
            Glide.with(profileImage)
                .load(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(profileImage);
        }
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
