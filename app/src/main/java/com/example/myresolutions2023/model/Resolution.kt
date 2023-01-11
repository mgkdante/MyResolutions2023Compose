package com.example.myresolutions2023.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Resolution(
    @StringRes val resolution: Int,
    @DrawableRes val resolutionImage: Int,
    @StringRes val resolutionNumber: Int
)