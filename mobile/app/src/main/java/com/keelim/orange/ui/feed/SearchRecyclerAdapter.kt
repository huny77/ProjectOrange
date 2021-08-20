package com.keelim.orange.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keelim.orange.R
import com.keelim.orange.data.model.Search
import com.keelim.orange.databinding.ItemSearchBinding

class SearchRecyclerAdapter(
    val clickListener:(Int, Int) -> Unit,
): ListAdapter<Search, SearchRecyclerAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding:ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:Search) = with(binding){

            title.text = item.title
            description.text = item.description
            val random = arrayOf(
                R.color.bg_orange, R.color.orange, R.color.orange_w,
            ).random()
            val color = ResourcesCompat.getColor(itemView.context.resources, random, null)

            container.setBackgroundColor(color)

            root.setOnClickListener {
                clickListener.invoke(item.challengeId, color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object:DiffUtil.ItemCallback<Search>(){
            override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.challengeId == newItem.challengeId
            }

            override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
                return oldItem.challengeId == newItem.challengeId
            }
        }
    }
}