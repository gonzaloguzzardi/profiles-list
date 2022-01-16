package com.guzzardi.profileslist.view.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.setActionBarTitle(title: String) {
    (activity as AppCompatActivity).supportActionBar?.title = title
}
