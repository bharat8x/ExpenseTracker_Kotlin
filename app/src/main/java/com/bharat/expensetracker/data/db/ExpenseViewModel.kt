package com.bharat.expensetracker.data.db

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    private val repository: ExpenseRepository
    val allWords: LiveData<List<Expense>>

    init {
        val wordsDao = ExpenseRoomDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(expense: Expense) = scope.launch(Dispatchers.IO) {
        repository.insert(expense)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}