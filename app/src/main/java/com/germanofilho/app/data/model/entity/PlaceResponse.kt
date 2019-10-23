package com.germanofilho.app.data.model.entity

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val results: List<Result>)

data class Result(val name: String,
                  val id: String,
                  val vicinity: String,
                  val geometry: Geometry,
                  @SerializedName("opening_hours")
                  val openingHours: OpeningHours)

data class Geometry(val location: Location)

data class Location(val lat: Double, val lng: Double)

data class OpeningHours(@SerializedName("open_now") val openNow: Boolean)