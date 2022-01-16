package com.guzzardi.profileslist.view.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.guzzardi.profileslist.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

const val TAKE_PHOTO_REQUEST_CODE = 101
const val CHOOSE_FROM_GALLERY_REQUEST_CODE = 102
const val CAMERA_PERMISSION_REQUEST_CODE = 1001

fun Fragment.openPhotoPickerDialog() {
    val activity = activity as? Activity ?: return
    val options = arrayOf<CharSequence>(
        // activity.getString(R.string.photo_picker_take_photo),
        activity.getString(R.string.photo_picker_take_choose_from_gallery),
        activity.getString(R.string.photo_picker_take_choose_cancel)
    )
    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    builder.setTitle(activity.getString(R.string.photo_picker_title))
    builder.setItems(options) { dialog, item ->
        when {
            options[item] == activity.getString(R.string.photo_picker_take_photo) -> {
                if (checkSelfPermission(
                        activity,
                        Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        activity,
                        arrayOf(Manifest.permission.CAMERA),
                        CAMERA_PERMISSION_REQUEST_CODE
                    )
                } else {
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    getCameraPhotoImageUri(activity)?.let { uri ->
                        takePicture.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    }
                    startActivityForResult(takePicture, TAKE_PHOTO_REQUEST_CODE)
                }
            }
            options[item] == activity.getString(R.string.photo_picker_take_choose_from_gallery) -> {
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, CHOOSE_FROM_GALLERY_REQUEST_CODE)
            }
            options[item] == activity.getString(R.string.photo_picker_take_choose_cancel) -> {
                dialog.dismiss()
            }
        }
    }
    builder.show()
}

@Suppress("ReturnCount")
fun getCameraPhotoImageUri(context: Context?): Uri? {
    if (context == null) return null

    val photoFile: File = try {
        createImageFile(context)
    } catch (ex: IOException) {
        null
    } ?: return null
    return FileProvider.getUriForFile(
            context,
            "com.guzzardi.profileslist.fileprovider",
            photoFile
        )
}

@Throws(IOException::class)
private fun createImageFile(context: Context): File? {
    // Create an image file name
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ROOT).format(Date())
    val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: return null
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}


