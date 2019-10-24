package com.germanofilho.app.feature.home.repository

import com.germanofilho.app.core.helper.CalcDistanceHelper
import com.germanofilho.app.core.service.ApiFactory
import com.germanofilho.app.data.model.entity.PlaceResponse
import com.germanofilho.app.data.model.entity.Result
import com.germanofilho.app.data.source.remote.api.NearbyPlaceApi

class HomeRepository: IHomeRepository{

    companion object{
        private const val RADIUS = 3500
        private const val CAFE = "cafe"
        private const val RESTAURANT = "restaurant"
        private const val BAR = "bar"
        private const val TYPES = "$CAFE|$RESTAURANT|$BAR"
    }

    override suspend fun fetchNearbyPlaces(lat: Double?, lng: Double?): PlaceResponse {
        if(isValidLocation(lat, lng)){
            return ApiFactory.request(NearbyPlaceApi::class.java).getNearbyPlaces(
                location = formatToLatLng(lat!!,lng!!),
                type = TYPES,
                radius = RADIUS).also {
                calculateDistance(it.results, lat, lng)
                it.results = it.results.sortedWith(compareBy { it.distance })
            }
        }

        throw Exception("Location invalid!")
    }

    private fun formatToLatLng(lat: Double, lng: Double): String{
        return lat.toString().plus(",").plus(lng)
    }

    private fun isValidLocation(lat: Double?, lng: Double?): Boolean {
        return lat != null && lng != null
    }

    private fun calculateDistance(results: List<Result>, lat: Double, lng: Double){
        for(result in results){
            result.distance = CalcDistanceHelper.getDistanceInKilometer(lat, lng,
                result.geometry.location.lat,
                result.geometry.location.lng)
        }
    }
}