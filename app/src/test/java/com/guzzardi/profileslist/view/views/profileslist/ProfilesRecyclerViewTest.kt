package com.guzzardi.profileslist.view.views.profileslist

import com.guzzardi.profileslist.RobolectricTest
import com.guzzardi.profileslist.model.UserProfile
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfilesRecyclerViewTest: RobolectricTest() {

    @Mock
    private lateinit var listener: ProfilesRecyclerView.OnProfileRemovedListener

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `setData calls submit list on ProfilesAdapter`() {
        val recyclerView = ProfilesRecyclerView(context)
        val testData = listOf(UserProfile("", "", "", null))

        recyclerView.setData(testData, listener)

        val adapter = recyclerView.adapter
        Assert.assertEquals(testData, (adapter as ProfilesAdapter).currentList)
    }
}
