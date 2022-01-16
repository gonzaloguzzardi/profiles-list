package com.guzzardi.profileslist.model

data class UserProfile(
    val givenName: String,
    val familyName: String,
    val email: String,
    val profileImagePath: ImageData?
)
