/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keelim.orange.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keelim.orange.data.model.entity.History
import com.keelim.orange.databinding.ItemSearchDetailBinding
import timber.log.Timber

class SearchAdapter(
    private val click: (String) -> Unit,
) : ListAdapter<History, SearchAdapter.SearchViewHolder>(diffUtil) {


    inner class SearchViewHolder(
        private val binding: ItemSearchDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: History) {
            binding.name.text = topic.keyword
            binding.name.setOnClickListener {
                Timber.d("왜 안넘어옴 ${topic.keyword}")
                click(topic.keyword)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemSearchDetailBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(
                oldItem: History,
                newItem: History,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: History,
                newItem: History,
            ): Boolean {
                return oldItem==newItem
            }
        }
    }
}

