package com.guzzardi.profileslist

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [28])
@RunWith(RobolectricTestRunner::class)
abstract class RobolectricTest {
    protected val context: Context = ApplicationProvider.getApplicationContext()
}