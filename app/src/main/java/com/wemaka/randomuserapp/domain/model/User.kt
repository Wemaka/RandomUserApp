package com.wemaka.randomuserapp.domain.model


import com.squareup.moshi.Json
import com.wemaka.randomuserapp.domain.entity.UserEntity

data class User(
    @field:Json(name = "cell")
    val cell: String,
    @field:Json(name = "dob")
    val dob: Dob,
    @field:Json(name = "email")
    val email: String,
    @field:Json(name = "gender")
    val gender: String,
    @field:Json(name = "id")
    val id: Id,
    @field:Json(name = "location")
    val location: Location,
    @field:Json(name = "login")
    val login: Login,
    @field:Json(name = "name")
    val name: Name,
    @field:Json(name = "nat")
    val nat: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "picture")
    val picture: Picture,
    @field:Json(name = "registered")
    val registered: Registered
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        uuid = login.uuid,
        first = name.first,
        last = name.last,
        title = name.title,
        email = email,
        gender = gender,
        nat = nat,
        homePhone = phone,
        cellPhone = cell,
        age = dob.age,
        dateOfBirth = dob.date,
        streetNumber = location.street.number,
        streetName = location.street.name,
        city = location.city,
        state = location.state,
        country = location.country,
        postcode = location.postcode,
        pictureUrl = picture.medium
    )
}