package com.germanofilho.app.feature.home.presentation.view.activity

interface IHomeView{
    fun showLoading(boolean: Boolean)
    fun showError()
    fun loadData()
    fun initWhenLocationReady()
    fun setupObservers()
}