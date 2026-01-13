package com.wemaka.randomuserapp.data.data_source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val uuid: String,
    val first: String,
    val last: String,
    val title: String,
    val email: String,
    val gender: String,
    val nat: String,
    val homePhone: String,
    val cellPhone: String,
    val age: Int,
    val dateOfBirth: String,
    val streetNumber: Int,
    val streetName: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val pictureUrl: String,
    val createdAt: Long = System.currentTimeMillis()
)