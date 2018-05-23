package com.codingstoic.employeevacationapp.registration

import android.util.Patterns
import com.codingstoic.employeevacationapp.user.ResponseStatus
import com.codingstoic.employeevacationapp.user.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface RegistrationPresenter {
    fun attachView(view: RegistrationView)
    fun removeView()
    fun registerUser(email: String, password: String, repeatPassword: String)
}

class RegistrationViewPresenter(val userRepository: UserRepository) : RegistrationPresenter {
    var view: RegistrationView? = null

    override fun attachView(view: RegistrationView) {
        this.view = view
    }

    override fun registerUser(email: String, password: String, repeatPassword: String) {
        if (email.isEmpty()) {
            view?.showErrorForEmailField("Email field empty!")
        }

        if (password.isEmpty()) {
            view?.showErrorForEmailField("Password field empty!")
        }

        if (repeatPassword.isEmpty()) {
            view?.showErrorForEmailField("Password field empty!")
        }

        if (password.length < 3) {
            view?.showErrorForPasswordField("Password too short!")
        }

        if (repeatPassword != password) {
            view?.showErrorForRepeatPasswordField("Passwords do not match!")
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            view?.showErrorForEmailField("Email not valid!")
        }

        if (emailAndPasswordIsValid(email, password, repeatPassword)) {
            view?.hideFormShowProgress()

            launch(UI) {
                val response = userRepository.createUser(email, password)
                view?.showFormHideProgress()
                when (response.status) {
                    ResponseStatus.SUCCESS -> view?.navigateToLoginScreen()
                    ResponseStatus.FAILURE -> view?.showServerError(response.error)
                }
            }
        }
    }

    override fun removeView() {
        view = null
    }


    private fun emailAndPasswordIsValid(email: String, password: String, repeatPassword: String) =
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()
             && password.length >= 3 && repeatPassword == password)
}