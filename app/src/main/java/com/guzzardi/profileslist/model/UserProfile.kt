package com.guzzardi.profileslist.model

import android.net.Uri

data class UserProfile(
    val givenName: String,
    val familyName: String,
    val email: String,
    val profileImageUri: Uri?
)
