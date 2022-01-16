package com.guzzardi.profileslist.view.views.profileslist

import com.guzzardi.profileslist.RobolectricTest
import com.guzzardi.profileslist.model.UserProfile
import org.junit.Assert
import org.junit.Test

class ProfilesRecyclerViewTest: RobolectricTest() {

    @Test
    fun `setData calls submit list on ProfilesAdapter`() {
        val recyclerView = ProfilesRecyclerView(context)
        val testData = listOf(UserProfile("", "", null))

        recyclerView.setData(testData)

        val adapter = recyclerView.adapter
        Assert.assertEquals(testData, (adapter as ProfilesAdapter).currentList)
    }
}
