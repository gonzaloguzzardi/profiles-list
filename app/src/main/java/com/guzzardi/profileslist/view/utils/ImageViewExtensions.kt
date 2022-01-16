package com.guzzardi.profileslist.view.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.guzzardi.profileslist.R

fun ImageView.setImageUri(context: Context, uri: Uri?) {
    Glide.with(context)
        .load(uri)
        .placeholder(R.mipmap.ic_launcher)
        .centerCrop()
        .error(R.mipmap.ic_launcher)
        .into(this)
}
