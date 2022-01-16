package com.guzzardi.profileslist.view.views.profileslist

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.guzzardi.profileslist.model.UserProfile


class ProfilesAdapter(private val onItemRemovedListener: ProfilesRecyclerView.OnProfileRemovedListener?
) : ListAdapter<UserProfile, RecyclerView.ViewHolder>(ProfileDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProfileViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ProfileViewHolder).bind(data)
    }

    fun removeItem(position: Int) {
        if (position >= itemCount) return
        val profileRemoved = currentList[position]
        onItemRemovedListener?.onRemoved(profileRemoved)
    }
}
