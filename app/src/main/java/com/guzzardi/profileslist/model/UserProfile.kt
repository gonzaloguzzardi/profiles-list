package com.guzzardi.profileslist.model

import android.net.Uri

data class UserProfile(
    val givenName: String,
    val familyName: String,
    val bio: String,
    val profileImageUri: Uri?
)
