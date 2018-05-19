package com.codingstoic.employeevacationapp.login

import com.codingstoic.employeevacationapp.user.UserRepository

/**
 * Created by Abdurahman Adilovic on 5/19/18.
 */

interface LoginPresenter<T : LoginView> {
    fun attachView(view: T)
    fun loginUser(email: String, password: String)
}

class LoginViewPresenter(val userRepository: UserRepository) : LoginPresenter<LoginView> {
    lateinit var view: LoginView

    override fun attachView(view: LoginView) {
        this.view = view
    }

    override fun loginUser(email: String, password: String) {

    }
}

