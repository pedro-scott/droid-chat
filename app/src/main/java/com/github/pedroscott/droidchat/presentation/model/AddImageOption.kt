package com.github.pedroscott.droidchat.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.pedroscott.droidchat.R

enum class AddImageOption(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int
) {
    TAKE(
        iconRes = R.drawable.ic_photo_camera,
        textRes = R.string.common_take_photo
    ),
    UPLOAD(
        iconRes = R.drawable.ic_photo_library,
        textRes = R.string.common_upload_photo
    );
}