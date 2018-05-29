package com.codingstoic.employeevacationapp.login

import android.util.Patterns
import com.codingstoic.employeevacationapp.base.BasePresenter
import com.codingstoic.employeevacationapp.user.ResponseStatus
import com.codingstoic.employeevacationapp.user.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * Created by Abdurahman Adilovic on 5/19/18.
 */

interface LoginPresenter : BasePresenter<LoginView> {
    fun loginUser(email: String, password: String)
}

class LoginViewPresenter(val userRepository: UserRepository) : LoginPresenter {
    var view: LoginView? = null

    override fun attachView(view: LoginView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun loginUser(email: String, password: String) {
        view?.let {
            if (email.isEmpty()) {
                it.showErrorForEmailField("Email field empty!")
            }
            if (password.isEmpty()) {
                it.showErrorForEmailField("Password field empty!")
            }

            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                it.hideFormShowProgress()
                launch(UI) {
                    val response = userRepository.loginUser(email, password)
                    it.showFormHideProgress()
                    when (response.status) {
                        ResponseStatus.SUCCESS -> it.navigateToVacationsScreen()
                        ResponseStatus.FAILURE -> it.showServerError(response.error)
                    }
                }
            } else {
                it.showErrorForEmailField("Email not valid!")
            }
        }
    }
}

