package com.codingstoic.employeevacationapp.base

/**
 * Created by Abdurahman Adilovic on 5/29/18.
 */

interface BasePresenter<T>{
    fun attachView(view: T)
    fun detachView()
}