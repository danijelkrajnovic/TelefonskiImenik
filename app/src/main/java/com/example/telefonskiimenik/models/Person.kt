package com.example.telefonskiimenik.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val id: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val email: String
) : Parcelable