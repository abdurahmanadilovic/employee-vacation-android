package com.codingstoic.employeevacationapp

import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

interface RegistrationPresenter<T : RegistrationView> {
    fun attachView(view: T)
    fun registerUser(email: String, password: String, repeatPassword: String)
}

class RegistrationViewPresenter : RegistrationPresenter<RegistrationView> {
    lateinit var view: RegistrationView

    override fun attachView(view: RegistrationView) {
        this.view = view
    }

    override fun registerUser(email: String, password: String, repeatPassword: String) {
        if (email.isEmpty()) {
            view.showErrorForEmailField("Email field empty!")
        }

        if (password.isEmpty()) {
            view.showErrorForEmailField("Password field empty!")
        }

        if (repeatPassword.isEmpty()) {
            view.showErrorForEmailField("Password field empty!")
        }

        if (password.length < 3) {
            view.showErrorForPasswordField("Password too short!")
        }

        if (repeatPassword != password) {
            view.showErrorForRepeatPasswordField("Passwords do not match!")
        }

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            && password.length >= 3 && repeatPassword == password) {
            view.hideFormShowProgress()
            launch(UI) {
                delay(3, TimeUnit.SECONDS)
                view.navigateToLoginScreen()
            }
        } else {
            view.showFormHideProgress()
        }
    }
}