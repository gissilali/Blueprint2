package com.silali.blueprint.entries

import androidx.annotation.Nullable
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieEntry

class EntriesController {
    fun fetchEntries(entryType: String?) : MutableList<Entry> {
        val entries = mutableListOf<Entry>()

        entries.add(Entry("black eyed peas concert expense", "23000", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert expense", "73000", "1 hour ago", "expense", "Home", "#4D8066"))
        entries.add(Entry("black eyed peas concert", "3040", "1 hour ago", "income", "Home", "#4D8066"))
        entries.add(Entry("black eyed peas concert expense", "230", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert", "3000", "1 hour ago", "income", "School", "#7e5ddd"))
        entries.add(Entry("black eyed peas concert expense", "20000", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert expense", "240", "1 hour ago", "expense", "Marijuana", "#4D8000"))
        entries.add(Entry("black eyed peas concert expense", "230", "1 hour ago", "expense", "Marijuana", "#4D8000"))
        entries.add(Entry("black eyed peas concert", "200", "1 hour ago", "income", "Groceries", "#809900"))
        entries.add(Entry("black eyed peas concert expense", "200", "1 hour ago", "expense", "Groceries", "#809900"))
        entries.add(Entry("black eyed peas concert expense", "7000", "1 hour ago", "expense", "Groceries", "#809900"))
        entries.add(Entry("black eyed peas concert expense", "8000", "1 hour ago", "expense", "Groceries", "#809900"))
        entries.add(Entry("black eyed peas concert expense", "1200", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert", "800", "1 hour ago", "income", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert expense", "300", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert expense", "24", "1 hour ago", "expense", "Entertainment", "#777ddd"))
        entries.add(Entry("black eyed peas concert", "3000", "1 hour ago", "income", "Entertainment", "#777ddd"))

        if (entryType == null) {
            return entries
        }

        return entries.filter { it.entryType == entryType }.toMutableList()
    }

    fun getExpenseOverView () : ArrayList<PieEntry> {
        var dataSet =  arrayListOf<PieEntry>()

        val entries = fetchEntries("expense")

        val groupedBy = entries.filter { it.entryType == "expense" }.groupBy { it.label }

        for(item in groupedBy) {
            var amount= 0
            for(entryItem in item.value) {
             amount += entryItem.amount.toInt()
            }
            dataSet.add(PieEntry(amount.toFloat(), item.key))
        }

        return dataSet
    }
}

