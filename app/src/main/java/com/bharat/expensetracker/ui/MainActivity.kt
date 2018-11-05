package com.bharat.expensetracker.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.EditText
import com.bharat.expensetracker.R
import com.bharat.expensetracker.data.db.Expense
import com.bharat.expensetracker.data.db.ExpenseViewModel
import com.bharat.expensetracker.utils.DatePickerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DatePickerFragment.OnCompleteListener {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var edtDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ExpenseListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        expenseViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adapter.setWords(it)
            }
        })

        fab.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog() {

        val layoutInflaterAndroid = LayoutInflater.from(this)
        val view = layoutInflaterAndroid.inflate(R.layout.item_add_expenses, null)

        val alertDialogBuilderUserInput = AlertDialog.Builder(this)
        alertDialogBuilderUserInput.setView(view)

        val edtItem = view.findViewById(R.id.edtDate) as EditText
        edtDate = view.findViewById(R.id.edtDate) as EditText
        val edtAmount = view.findViewById(R.id.edtAmount) as EditText

        edtDate.setOnClickListener {
            val newFragment = DatePickerFragment()
            newFragment.show(getSupportFragmentManager(), "datePicker")
        }

        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton(
                android.R.string.ok
            ) { dialogBox, id ->
                val expense =
                    Expense(edtItem.text.toString(), edtDate.text.toString(), edtAmount.text.toString().toDouble())
                expenseViewModel.insert(expense)
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialogBox, id -> dialogBox.cancel() }

        val alertDialog = alertDialogBuilderUserInput.create()
        alertDialog.show()

    }

    override fun onComplete(time: String) {
        edtDate.setText(time)
    }

}
