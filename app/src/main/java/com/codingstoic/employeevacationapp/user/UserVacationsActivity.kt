package com.codingstoic.employeevacationapp.user

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.codingstoic.employeevacationapp.R

import kotlinx.android.synthetic.main.activity_user_vacations.*

class UserVacationsActivity : AppCompatActivity() {
    // todo recycler view with list of vacations
    // vacations model
    // presenter
    // vacations repository, get vacations for userID
    // floating button to create new vacation request
    // loading spinner
    // swipe to delete option with confirmation
    // click to edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_vacations)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
