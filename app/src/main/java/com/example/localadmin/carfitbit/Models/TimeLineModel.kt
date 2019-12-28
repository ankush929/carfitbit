package com.example.localadmin.carfitbit.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TimeLineModel(
    var message: String,
    var date: String,
    var status: OrderStatus
) : Parcelable