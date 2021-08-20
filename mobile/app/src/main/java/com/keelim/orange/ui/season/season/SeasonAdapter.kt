package com.keelim.orange.ui.season.season

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keelim.orange.R
import com.keelim.orange.data.model.season.Article
import com.keelim.orange.databinding.ItemSeasonBinding

class SeasonAdapter(
    private val more: (Article) -> Unit,
    private val comment: (Article) -> Unit,
) : ListAdapter<Article, SeasonAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) = with(binding) {
            if (item.verified.not()) {
                verified.visibility = View.INVISIBLE
            }
            articleTitle.text = item.title
            user.text = item.user.toString()
            if(item.articleImage ==""){
                articleImage.load(R.drawable.morning)
            } else{
                articleImage.load(item.articleImage)
            }
            date.text = item.articleWritetime
            btnComment.text = item.articleContent

            btnMore.setOnClickListener {
                more.invoke(item)
            }

            btnComment.setOnClickListener {
                comment.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSeasonBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}