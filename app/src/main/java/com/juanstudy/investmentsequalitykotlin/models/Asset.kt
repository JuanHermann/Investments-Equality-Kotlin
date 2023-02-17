package com.juanstudy.investmentsequalitykotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("assets")
data class Asset(
    @PrimaryKey(true) val id: Int? = null,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "data_com") val dataCom: Int
) {


    constructor(tag: String) : this(null, tag, 0, 0.0, 0) {

    }
}
