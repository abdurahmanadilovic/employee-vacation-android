package com.codingstoic.employeevacationapp.login

import android.util.Patterns
import com.codingstoic.employeevacationapp.user.ResponseStatus
import com.codingstoic.employeevacationapp.user.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

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
        if (email.isEmpty()) {
            view.showErrorForEmailField("Email field empty!")
        }
        if (password.isEmpty()) {
            view.showErrorForEmailField("Password field empty!")
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.hideFormShowProgress()
            launch(UI) {
                val response = userRepository.loginUser(email, password)
                view.showFormHideProgress()
                when (response.status) {
                    ResponseStatus.SUCCESS -> view.navigateToVacationsScreen()
                    ResponseStatus.FAILURE -> view.showServerError(response.error)
                }
            }
        } else {
            view.showErrorForEmailField("Email not valid!")
        }
    }
}

