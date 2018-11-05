package com.bharat.expensetracker.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "expense_table")
class Expense(

    @ColumnInfo(name = "Item") val item: String,
    @ColumnInfo(name = "Date") val date: String,
    @ColumnInfo(name = "Amount") val amount: Double

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}