package com.bharat.expensetracker.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ExpenseDao {

    @Query("SELECT * from expense_table ORDER BY Date ASC")
    fun getAllItemList(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expense: Expense)
}