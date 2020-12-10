package com.wednesdaykotlinpractise.models

import com.google.gson.annotations.SerializedName

data class Bank (
    var id : Int? = null,
    @SerializedName("BANK") var bank : String? = null,
    @SerializedName("IFSC") var ifsc : String? = null,
    @SerializedName("BRANCH")var branch : String? = null,
    @SerializedName("ADDRESS") var address : String? = null,
    @SerializedName("CONTACT") var contact : String? = null,
    @SerializedName("CITY") var city : String? = null,
    @SerializedName("RTGS") var rtgs : Boolean? = null,
    @SerializedName("DISTRICT") var district : String? = null,
    @SerializedName("STATE") var state : String? = null
)
