package com.sabrina.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.sabrina.domain.model.User

fun FirebaseUser.toDomain(): User {
    return User(
        uid = this.uid,
        email = this.email
    )
}