package com.germanofilho.app.feature.home.repository

import com.germanofilho.app.data.model.entity.PlaceResponse

interface IHomeRepository {
    suspend fun fetchNearbyPlaces(lat: Double?, lng: Double?) : PlaceResponse
}