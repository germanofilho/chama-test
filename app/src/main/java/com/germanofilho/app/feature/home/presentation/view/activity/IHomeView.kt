package com.germanofilho.app.feature.home.presentation.view.activity

import com.germanofilho.app.data.model.entity.Result

interface IHomeView{
    fun showLoading(boolean: Boolean)
    fun showError()
    fun loadData(results: List<Result>)
    fun initWhenLocationReady()
    fun setupObservers()
}