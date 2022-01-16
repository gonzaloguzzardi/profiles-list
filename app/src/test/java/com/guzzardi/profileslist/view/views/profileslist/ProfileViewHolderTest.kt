package com.guzzardi.profileslist.view.views.profileslist

import android.view.View
import android.widget.FrameLayout
import com.guzzardi.profileslist.RobolectricTest
import com.guzzardi.profileslist.model.UserProfile
import org.junit.Assert
import org.junit.Test

class ProfileViewHolderTest : RobolectricTest() {

    @Test
    fun `bind correctly displays definition text value`() {
        val viewHolder = createViewHolder()

        viewHolder.bind(UserProfile(GIVEN_NAME, FAMILY_NAME, "", null))

        Assert.assertEquals(View.VISIBLE, viewHolder.fullName.visibility)
        Assert.assertEquals("$GIVEN_NAME $FAMILY_NAME", viewHolder.fullName.text)
    }


    private fun createViewHolder() =
        ProfileViewHolder.create(FrameLayout(context)) as ProfileViewHolder

    private companion object {
        const val GIVEN_NAME = "Test"
        const val FAMILY_NAME = "McTesting"
    }
}