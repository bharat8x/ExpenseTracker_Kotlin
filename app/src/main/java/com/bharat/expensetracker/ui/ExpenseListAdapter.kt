package com.bharat.expensetracker.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bharat.expensetracker.R
import com.bharat.expensetracker.data.db.Expense
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ExpenseListAdapter internal constructor(val context: Context) :
    RecyclerView.Adapter<ExpenseListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var expense = emptyList<Expense>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem = itemView.tvItem
        val tvDate = itemView.tvDate
        val tvAmount = itemView.tvAmount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = expense[position]

        holder.tvItem.text = current.item
        holder.tvDate.text = current.date
        holder.tvAmount.text = "" + current.amount
    }

    internal fun setWords(expense: List<Expense>) {
        this.expense = expense
        notifyDataSetChanged()
    }

    override fun getItemCount() = expense.size

}