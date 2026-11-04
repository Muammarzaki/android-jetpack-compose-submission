package com.dicoding

import android.net.Uri

data class Country(
    val name: String,
    val countryFlagId: Int,
    val detailWikiLink: Uri
)
