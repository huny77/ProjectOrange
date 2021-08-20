package com.keelim.orange.ui.season.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keelim.orange.R
import com.keelim.orange.data.model.ranking.Ranking
import com.keelim.orange.databinding.ItemRankingBinding

class RankingAdapter(
    private val longClick: (Ranking) -> Unit,
) : ListAdapter<Ranking, RankingAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding: ItemRankingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ranking) = with(binding) {

            title.text = item.ranking_title
            number.text = item.rank.toString()
            val random = arrayOf(
                R.color.bg_orange, R.color.orange, R.color.orange_w,
            ).random()
            val color = itemView.context.resources.getColor(random)
            container.setBackgroundColor(color)

            root.setOnLongClickListener {
                longClick.invoke(item)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Ranking>() {
            override fun areItemsTheSame(oldItem: Ranking, newItem: Ranking): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Ranking, newItem: Ranking): Boolean {
                return oldItem == newItem
            }
        }
    }
}