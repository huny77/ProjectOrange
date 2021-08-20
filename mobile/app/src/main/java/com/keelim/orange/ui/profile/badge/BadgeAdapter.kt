package com.keelim.orange.ui.profile.badge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keelim.orange.data.model.Badge
import com.keelim.orange.databinding.ItemBadgeBinding

class BadgeAdapter(
  val clickListener: () -> Unit,
) : ListAdapter<Badge, BadgeAdapter.ViewHolder>(diffUtil) {

  inner class ViewHolder(val binding: ItemBadgeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Badge) = with(binding) {
      badgeTitle.text = item.title
      badgeDescription.text = item.description
      badgeImg.load(item.image_path.toInt())

      root.setOnClickListener {
        clickListener.invoke()
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ItemBadgeBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(currentList[position])
  }

  companion object {
    val diffUtil = object : DiffUtil.ItemCallback<Badge>() {
      override fun areItemsTheSame(oldItem: Badge, newItem: Badge): Boolean {
        return oldItem.title == newItem.title
      }

      override fun areContentsTheSame(oldItem: Badge, newItem: Badge): Boolean {
        return oldItem.image_path == newItem.image_path
      }
    }
  }
}
