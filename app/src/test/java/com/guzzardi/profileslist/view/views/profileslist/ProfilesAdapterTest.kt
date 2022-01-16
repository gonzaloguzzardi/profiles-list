package com.guzzardi.profileslist.view.views.profileslist

import android.widget.FrameLayout
import com.guzzardi.profileslist.RobolectricTest
import com.guzzardi.profileslist.model.UserProfile
import org.junit.Assert
import org.junit.Test

class ProfilesAdapterTest : RobolectricTest() {

    @Test
    fun `OnBindViewHolder binds user profile data to ProfileViewHolder`() {
        val adapter = ProfilesAdapter()
        val viewHolder = adapter.onCreateViewHolder(FrameLayout(context), 0)

        val data = UserProfile(GIVEN_NAME, FAMILY_NAME, "", null)

        (viewHolder as ProfileViewHolder).bind(data)

        Assert.assertEquals("Test McTesting", viewHolder.fullName.text)

    }

    private companion object {
        const val GIVEN_NAME = "Test"
        const val FAMILY_NAME = "McTesting"
    }
}