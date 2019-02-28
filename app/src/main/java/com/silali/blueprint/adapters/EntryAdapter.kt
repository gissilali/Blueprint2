package com.silali.blueprint.adapters

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.silali.blueprint.entries.Entry
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.silali.blueprint.R
import org.jetbrains.anko.find


class EntryAdapter(val entries : List<Entry>) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.entry_list_item, parent, false)

        // Return a new holder instance
        return EntryViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    class EntryViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView) {
        val titleView : TextView = itemView.findViewById(R.id.text_entry_item_title)
        val amountView : TextView = itemView.findViewById(R.id.text_entry_item_amount)
        val dateTimeView : TextView = itemView.findViewById(R.id.text_entry_item_datetime)
        val labelView : TextView = itemView.findViewById(R.id.text_entry_item_label)
        val labelColorView : View = itemView.findViewById(R.id.view_entry_item_label_color_dot)

        fun bind(entry : Entry) {
            titleView.text = entry.title
            amountView.text = entry.amount
            dateTimeView.text = entry.time
            labelView.text = entry.label
            labelColorView.setBackgroundColor(Color.parseColor(entry.labelColor))
        }
    }
}