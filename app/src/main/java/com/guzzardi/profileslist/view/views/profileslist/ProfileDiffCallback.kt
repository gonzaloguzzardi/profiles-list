package com.guzzardi.profileslist.view.views.profileslist

import androidx.recyclerview.widget.DiffUtil
import com.guzzardi.profileslist.model.UserProfile

class ProfileDiffCallback : DiffUtil.ItemCallback<UserProfile>() {
    override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean =
        oldItem == newItem
}
