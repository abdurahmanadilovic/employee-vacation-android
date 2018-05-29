package com.codingstoic.employeevacationapp.registration

import android.util.Patterns
import com.codingstoic.employeevacationapp.base.BasePresenter
import com.codingstoic.employeevacationapp.user.ResponseStatus
import com.codingstoic.employeevacationapp.user.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

interface RegistrationPresenter : BasePresenter<RegistrationView> {
    fun registerUser(email: String, password: String, repeatPassword: String)
}

class RegistrationViewPresenter(val userRepository: UserRepository) : RegistrationPresenter {
    var view: RegistrationView? = null

    override fun attachView(view: RegistrationView) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun registerUser(email: String, password: String, repeatPassword: String) {
        view?.let {
            if (email.isEmpty()) {
                it.showErrorForEmailField("Email field empty!")
            }

            if (password.isEmpty()) {
                it.showErrorForEmailField("Password field empty!")
            }

            if (repeatPassword.isEmpty()) {
                it.showErrorForEmailField("Password field empty!")
            }

            if (password.length < 3) {
                it.showErrorForPasswordField("Password too short!")
            }

            if (repeatPassword != password) {
                it.showErrorForRepeatPasswordField("Passwords do not match!")
            }

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
                it.showErrorForEmailField("Email not valid!")
            }

            if (emailAndPasswordIsValid(email, password, repeatPassword)) {
                it.hideFormShowProgress()

                launch(UI) {
                    val response = userRepository.createUser(email, password)
                    it.showFormHideProgress()
                    when (response.status) {
                        ResponseStatus.SUCCESS -> it.navigateToLoginScreen()
                        ResponseStatus.FAILURE -> it.showServerError(response.error)
                    }
                }
            }
        }
    }

    private fun emailAndPasswordIsValid(email: String, password: String, repeatPassword: String) =
            (Patterns.EMAIL_ADDRESS.matcher(email).matches()
             && password.length >= 3 && repeatPassword == password)
}