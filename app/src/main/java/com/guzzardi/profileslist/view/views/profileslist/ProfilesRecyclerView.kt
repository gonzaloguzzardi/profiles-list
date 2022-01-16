package com.guzzardi.profileslist.view.views.profileslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guzzardi.profileslist.model.UserProfile


class ProfilesRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    interface OnProfileRemovedListener {
        fun onRemoved(userProfile: UserProfile)
    }

    private val itemTouchCallback = PostTouchHelperCallback(this)

    init {
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        addItemDecoration(dividerItemDecoration)
        setItemTouchCallback()
    }

    fun setData(profiles: List<UserProfile>, listener: OnProfileRemovedListener) {
        val profilesAdapter = ProfilesAdapter(listener)
        adapter = profilesAdapter
        profilesAdapter.submitList(profiles)
    }

    private fun setItemTouchCallback() {
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(this)
    }
}
