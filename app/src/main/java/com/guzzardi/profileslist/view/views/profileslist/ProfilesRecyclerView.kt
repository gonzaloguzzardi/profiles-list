package com.guzzardi.profileslist.view.views.profileslist

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guzzardi.profileslist.model.UserProfile


class ProfilesRecyclerView(context: Context, attrs: AttributeSet? = null) :
    RecyclerView(context, attrs) {

    init {
        setHasFixedSize(true)
        overScrollMode = OVER_SCROLL_NEVER
        layoutManager = LinearLayoutManager(context)
        adapter = ProfilesAdapter()
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        addItemDecoration(dividerItemDecoration)
    }

    fun setData(profiles: List<UserProfile>) {
        (adapter as ProfilesAdapter).submitList(profiles)
    }
}
