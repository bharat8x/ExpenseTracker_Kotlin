package com.bharat.expensetracker.data.db

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allWords: LiveData<List<Expense>> = expenseDao.getAllItemList()

    @WorkerThread
    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }
}