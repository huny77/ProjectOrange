package com.keelim.orange.ui.profile.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keelim.orange.data.model.entity.Favorite
import com.keelim.orange.databinding.ItemFavoriteBinding
import com.keelim.orange.utils.ToggleAnimation

class FavoriteAdapter(
  private val delete: (Favorite) -> Unit
) : ListAdapter<Favorite, FavoriteAdapter.ViewHolder>(diffUtil) {

  inner class ViewHolder(val binding: ItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Favorite, position: Int) = with(binding) {
      favoriteTitle.text = item.ranking_title
      favoriteCount.text = item.rank.toString()
      favoriteImg.load(item.image_address)
      subDescription.text = item.ranking_description

      btnExpand.setOnClickListener {
        val show = toggleLayout(!item.isFull, it, layoutExpand)
        item.isFull = show
      }

      btnDelete.setOnClickListener {
        delete.invoke(item)
      }
    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
      // 2
      ToggleAnimation.toggleArrow(view, isExpanded)
      if (isExpanded) {
        ToggleAnimation.expand(layoutExpand)
      } else {
        ToggleAnimation.collapse(layoutExpand)
      }
      return isExpanded
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemFavoriteBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(currentList[position], position)
  }

  companion object {
    val diffUtil = object : DiffUtil.ItemCallback<Favorite>() {
      override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem == newItem
      }

      override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
        return oldItem == newItem
      }
    }
  }
}
