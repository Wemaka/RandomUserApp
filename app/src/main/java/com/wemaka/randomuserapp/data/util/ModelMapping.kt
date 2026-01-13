package com.wemaka.randomuserapp.data.util

import com.wemaka.randomuserapp.data.data_source.local.model.UserEntity
import com.wemaka.randomuserapp.data.data_source.remote.model.RemoteUser
import com.wemaka.randomuserapp.data.model.User

fun RemoteUser.toLocal(): UserEntity {
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

@JvmName("remoteToLocal")
fun List<RemoteUser>.toLocal(): List<UserEntity> {
    return map(RemoteUser::toLocal)
}

fun User.toLocal(): UserEntity {
    return UserEntity(
        id = id,
        uuid = uuid,
        first = first,
        last = last,
        title = title,
        email = email,
        gender = gender,
        nat = nat,
        homePhone = homePhone,
        cellPhone = cellPhone,
        age = age,
        dateOfBirth = dateOfBirth,
        streetNumber = streetNumber,
        streetName = streetName,
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        pictureUrl = pictureUrl,
        createdAt = createdAt
    )
}

@JvmName("externalToLocal")
fun List<User>.toLocal(): List<UserEntity> {
    return map(User::toLocal)
}


fun RemoteUser.toExternal(): User {
    return toLocal().toExternal()
}

@JvmName("remoteToExternal")
fun List<RemoteUser>.toExternal(): List<User> {
    return map(RemoteUser::toExternal)
}

fun UserEntity.toExternal(): User {
    return User(
        id = id,
        uuid = uuid,
        first = first,
        last = last,
        title = title,
        email = email,
        gender = gender,
        nat = nat,
        homePhone = homePhone,
        cellPhone = cellPhone,
        age = age,
        dateOfBirth = dateOfBirth,
        streetNumber = streetNumber,
        streetName = streetName,
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        pictureUrl = pictureUrl,
        createdAt = createdAt
    )
}

@JvmName("localToExternal")
fun List<UserEntity>.toExternal(): List<User> {
    return map(UserEntity::toExternal)
}
