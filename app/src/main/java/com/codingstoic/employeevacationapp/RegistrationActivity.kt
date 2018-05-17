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

    private fun hideFormShowProgress() {
        changeVisibilityForView(view = activity_registration_content_group, visibility = View.INVISIBLE)
        changeVisibilityForView(view = activity_registration_progress_bar, visibility = View.VISIBLE)
    }

    override fun showRegistrationFormHideProgress() {
        changeVisibilityForView(view = activity_registration_content_group, visibility = View.VISIBLE)
        changeVisibilityForView(view = activity_registration_progress_bar, visibility = View.INVISIBLE)
    }

    private fun changeVisibilityForView(view: View, visibility: Int) {
        view.visibility = visibility
    }

    override fun showErrorForPasssordFeild(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorForRepeatPasssordFeild(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorForEmailField(error: String) {
        activity_registration_email_input_layout.error = error
    }

    override fun navigateToHomeScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

interface RegistrationView {
    fun showErrorForPasssordFeild(error: String)
    fun showErrorForRepeatPasssordFeild(error: String)
    fun showErrorForEmailField(error: String)
    fun navigateToHomeScreen()
    fun showRegistrationFormHideProgress()
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
        launch(UI) {
            delay(3, TimeUnit.SECONDS)
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            } else {
                view.showRegistrationFormHideProgress()
                view.showErrorForEmailField("Email not valid")
            }
        }
    }
}

