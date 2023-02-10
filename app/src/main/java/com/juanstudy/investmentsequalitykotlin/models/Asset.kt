package com.juanstudy.investmentsequalitykotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("assets")
data class Asset(@PrimaryKey(true) val id: Int, val tag: String, val quantity: Int, val price: Double, val dataCom: Int) {
}
