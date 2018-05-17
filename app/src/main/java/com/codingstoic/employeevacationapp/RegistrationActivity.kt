package com.codingstoic.employeevacationapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import java.util.concurrent.TimeUnit

class RegistrationActivity : AppCompatActivity(), RegistrationView {

    private val presenter: RegistrationPresenter<RegistrationView> = RegistrationViewPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        presenter.attachView(this)
        ButterKnife.bind(this)
    }

    fun createUserButtonClicked(view: View) {
        hideFormShowProgress()

        presenter.registerUser(
                activity_registration_email_input_edit_text.text.toString(),
                activity_registration_password_input_edit_text.text.toString(),
                activity_registration_repeat_password_input_edit_text.text.toString())
    }

    override fun hideFormShowProgress() {
        changeVisibilityForView(view = activity_registration_content_group, visibility = View.GONE)
        changeVisibilityForView(view = activity_registration_progress_bar, visibility = View.VISIBLE)
    }

    override fun showFormHideProgress() {
        changeVisibilityForView(view = activity_registration_content_group, visibility = View.VISIBLE)
        changeVisibilityForView(view = activity_registration_progress_bar, visibility = View.GONE)
    }

    private fun changeVisibilityForView(view: View, visibility: Int) {
        view.visibility = visibility
    }

    override fun showErrorForPasssordFeild(error: String) {
        activity_registration_password_input_layout.error = error
    }

    override fun showErrorForRepeatPasssordFeild(error: String) {
        activity_registration_repeat_password_input_layout.error = error
    }

    override fun showErrorForEmailField(error: String) {
        activity_registration_email_input_layout.error = error
    }

    override fun navigateToLoginScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

interface RegistrationView {
    fun showErrorForPasssordFeild(error: String)
    fun showErrorForRepeatPasssordFeild(error: String)
    fun showErrorForEmailField(error: String)
    fun navigateToLoginScreen()
    fun showFormHideProgress()
    fun hideFormShowProgress()
}

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
            view.showErrorForPasssordFeild("Password too short!")
        }

        if (repeatPassword != password) {
            view.showErrorForRepeatPasssordFeild("Passwords do not match!")
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

