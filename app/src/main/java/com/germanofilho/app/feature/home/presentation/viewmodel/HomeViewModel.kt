package com.germanofilho.app.feature.home.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.germanofilho.app.core.helper.Resource
import com.germanofilho.app.core.viewmodel.BaseViewModel
import com.germanofilho.app.data.model.entity.PlaceResponse
import com.germanofilho.app.feature.home.repository.IHomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: IHomeRepository): BaseViewModel(), IHomeViewModel {

    val nearbyPlaces = MutableLiveData<Resource<PlaceResponse>>()

    override fun getNearbyPlaces(lat: Double?, lng: Double?) {
        viewModelScope.launch {
            nearbyPlaces.loading(true)
            try {
                nearbyPlaces.success(repository.fetchNearbyPlaces(lat, lng).let { it })
            } catch (e: Exception) {
                nearbyPlaces.error(e)
            } finally {
                nearbyPlaces.loading(false)
            }
        }
    }
}