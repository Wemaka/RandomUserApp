package com.wemaka.randomuserapp.domain.model

import androidx.annotation.DrawableRes

data class InfoItem(
    val title: String,
    val content: String,
    @DrawableRes val iconRes: Int? = null
)

