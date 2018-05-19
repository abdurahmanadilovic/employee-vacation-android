package com.codingstoic.employeevacationapp.user

/**
 * Created by Abdurahman Adilovic on 5/17/18.
 */

class UserRepository {
    suspend fun createUser(email: String, password: String): Response<User> {
        return Response(User(1), ResponseStatus.SUCCESS, "")
    }

    suspend fun loginUser(email: String, password: String): Response<User> {
        return Response(User(1), ResponseStatus.SUCCESS, "")
    }
}

enum class ResponseStatus {
    SUCCESS, FAILURE
}

data class Response<T>(val data: T, val status: ResponseStatus, val error: String)