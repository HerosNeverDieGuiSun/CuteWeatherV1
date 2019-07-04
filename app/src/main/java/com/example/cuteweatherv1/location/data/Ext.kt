package com.example.cuteweatherv1.location.data


import com.google.gson.annotations.SerializedName

data class Ext(
    @SerializedName("adcode")
    val adcode: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("city_level")
    val cityLevel: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: Int,
    @SerializedName("country_code_iso")
    val countryCodeIso: String,
    @SerializedName("country_code_iso2")
    val countryCodeIso2: String,
    @SerializedName("direction")
    val direction: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("street_number")
    val streetNumber: String,
    @SerializedName("town")
    val town: String
)