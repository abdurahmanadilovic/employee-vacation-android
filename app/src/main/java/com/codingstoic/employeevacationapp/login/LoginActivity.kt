package com.codingstoic.employeevacationapp.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.codingstoic.employeevacationapp.R

interface LoginView {
    fun showErrorForPasswordField(error: String)
    fun showErrorForEmailField(error: String)
    fun showServerError(error: String)
    fun showFormHideProgress()
    fun hideFormShowProgress()
    fun navigateToVacationsScreen()
}

class LoginActivity : AppCompatActivity(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginButtonClicked(view: View) {

    }

    override fun showErrorForPasswordField(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorForEmailField(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showServerError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFormHideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideFormShowProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToVacationsScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

