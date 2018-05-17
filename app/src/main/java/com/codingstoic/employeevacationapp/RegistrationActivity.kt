package com.codingstoic.employeevacationapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity(), RegistrationView {
    private val presenter: RegistrationPresenter<RegistrationView> =
            RegistrationViewPresenter(userRepository = UserRepository())

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

    override fun showErrorForPasswordField(error: String) {
        activity_registration_password_input_layout.error = error
    }

    override fun showErrorForRepeatPasswordField(error: String) {
        activity_registration_repeat_password_input_layout.error = error
    }

    override fun showErrorForEmailField(error: String) {
        activity_registration_email_input_layout.error = error
    }

    override fun navigateToLoginScreen() {

    }

    override fun showServerError(error: String) {

    }

}

interface RegistrationView {
    fun showErrorForPasswordField(error: String)
    fun showErrorForRepeatPasswordField(error: String)
    fun showErrorForEmailField(error: String)
    fun navigateToLoginScreen()
    fun showFormHideProgress()
    fun hideFormShowProgress()
    fun showServerError(error: String)
}

