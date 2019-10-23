package com.germanofilho.app.feature.home.repository

import com.germanofilho.app.core.service.ApiFactory
import com.germanofilho.app.data.model.entity.PlaceResponse
import com.germanofilho.app.data.source.remote.api.NearbyPlaceApi

class HomeRepository: IHomeRepository{

    companion object{
        const val RADIUS = 3500
        const val CAFE = "cafe"
        const val RESTAURANT = "restaurant"
        const val BAR = "bar"
        const val TYPES = "$CAFE|$RESTAURANT|$BAR"
    }

    override suspend fun fetchNearbyPlaces(lat: Double?, lng: Double?): PlaceResponse {
        if(isValidLocation(lat, lng)){
            return ApiFactory.request(NearbyPlaceApi::class.java).getNearbyPlaces(
                location = formatToLatLng(lat!!,lng!!),
                type = TYPES,
                radius = RADIUS)
        }

        throw Exception("Location invalid!")
    }

    private fun formatToLatLng(lat: Double, lng: Double): String{
        return lat.toString().plus(",").plus(lng)
    }

    private fun isValidLocation(lat: Double?, lng: Double?): Boolean {
        return lat != null && lng != null
    }

}