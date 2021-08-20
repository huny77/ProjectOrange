package com.keelim.orange.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keelim.orange.R
import com.keelim.orange.data.model.Search2
import com.keelim.orange.databinding.ItemSearch2Binding

class SearchRecyclerAdapter2(
    val clickListener:(Search2) -> Unit,
): ListAdapter<Search2, SearchRecyclerAdapter2.ViewHolder>(diffUtil) {

    inner class ViewHolder(val binding:ItemSearch2Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:Search2) = with(binding){
            if ("" == item.imagePath) {
                imgChallenge.load(R.drawable.morning)
            } else {
                imgChallenge.load("http://i5b102.p.ssafy.io:8181/api/image/show/${item.imagePath}")
            }
            title.text = item.challengeTitle
            describe.text = item.challengeDescribe
            managerId.text = "관리자 id: ${item.managerId}"
            member.text = "${item.minMembers} ~ ${item.maxMembers} 현재: ${item.currentMembers}"
            startDate.text = "시작 날짜: ${item.startDate}"
            point.text = "종합 포인: ${item.totalPoint}"

            container.setOnClickListener {
                clickListener.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSearch2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object:DiffUtil.ItemCallback<Search2>(){
            override fun areItemsTheSame(oldItem: Search2, newItem: Search2): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Search2, newItem: Search2): Boolean {
                return oldItem == newItem
            }
        }
    }
}