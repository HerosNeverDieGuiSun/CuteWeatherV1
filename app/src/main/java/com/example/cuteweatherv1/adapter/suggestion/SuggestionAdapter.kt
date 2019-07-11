package com.example.cuteweatherv1.adapter.suggestion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cuteweatherv1.repository.lifeSuggestion.LifeSuggestions
import com.example.cuteweatherv1.repository.module.DealModuleInfo
import com.example.cuteweatherv1.repository.module.ModuleRepository
import kotlinx.android.synthetic.main.suggestion_item.view.*

class SuggestionAdapter (
    val context: Context,
    val itemLayout:Int,
    val data:ArrayList<LifeSuggestions>
): RecyclerView.Adapter<SuggestionAdapter.ViewHolder> () {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SuggestionAdapter.ViewHolder, position: Int) {
        val suggestions = data[position]
        holder.image.setImageResource(suggestions.image)
        holder.title.text = suggestions.title
        holder.info.text = suggestions.info
        holder.suggest.text = suggestions.suggestion
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(itemLayout, parent, false)
        return ViewHolder(itemView)
    }

    inner class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.imageViewSuggest
        val title = itemView.title
        val info = itemView.info
        val suggest = itemView.suggest
    }
}