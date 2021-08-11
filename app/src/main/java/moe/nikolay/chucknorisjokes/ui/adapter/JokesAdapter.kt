package moe.nikolay.chucknorisjokes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import moe.nikolay.chucknorisjokes.R

class JokesAdapter: RecyclerView.Adapter<JokesAdapter.ViewHolder>() {
    private var mData = mutableListOf<String>()

    class ViewHolder internal constructor(view: View, val viewType: Int) : RecyclerView.ViewHolder(view) {
        val joke_text = view.findViewById<TextView>(R.id.joke_text)
    }

    fun setData(data: List<String>) {
        mData = data as MutableList<String>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.component_joke_list_item, parent, false)
        return ViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mData.isNotEmpty()) holder.joke_text.text = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}