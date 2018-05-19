package com.codingstoic.employeevacationapp.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.codingstoic.employeevacationapp.R
import com.codingstoic.employeevacationapp.user.UserRepository
import kotlinx.android.synthetic.main.activity_login.*

interface LoginView {
    fun showErrorForPasswordField(error: String)
    fun showErrorForEmailField(error: String)
    fun showServerError(error: String)
    fun showFormHideProgress()
    fun hideFormShowProgress()
    fun navigateToVacationsScreen()
}

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var presenter: LoginPresenter<LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginViewPresenter(UserRepository())
        presenter.attachView(this)
    }

    fun loginButtonClicked(view: View) {
        presenter.loginUser(
                activity_login_email_input_edit_text.text.toString(),
                activity_login_password_input_edit_text.text.toString())
    }

    override fun showErrorForPasswordField(error: String) {
        activity_login_password_input_layout.error = error
    }

    override fun showErrorForEmailField(error: String) {
        activity_login_email_input_layout.error = error
    }

    override fun showServerError(error: String) {
        // todo implement snack bar
    }

    override fun showFormHideProgress() {
        activity_login_content_group.visibility = View.GONE
        activity_login_progress_bar.visibility = View.VISIBLE
    }

    override fun hideFormShowProgress() {
        activity_login_content_group.visibility = View.VISIBLE
        activity_login_progress_bar.visibility = View.GONE
    }

    override fun navigateToVacationsScreen() {

    }
}

