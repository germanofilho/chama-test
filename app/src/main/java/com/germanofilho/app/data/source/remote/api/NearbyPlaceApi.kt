package com.germanofilho.app.data.source.remote.api

import com.germanofilho.app.data.model.entity.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyPlaceApi {

    @GET("place/nearbysearch/json")
    suspend fun getNearbyPlaces(@Query("location") location: String,
                                @Query("types") type: String,
                                @Query("radius") radius: Int) : PlaceResponse
}