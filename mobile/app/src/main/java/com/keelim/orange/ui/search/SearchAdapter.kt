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
import com.keelim.orange.data.model.Sample
import com.keelim.orange.databinding.ItemSearchDetailBinding

class SearchAdapter : ListAdapter<Sample, SearchAdapter.SearchViewHolder>(diffUtil) {


    inner class SearchViewHolder(
        private val binding: ItemSearchDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topic: Sample) {
            binding.name.text = topic.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object :DiffUtil.ItemCallback<Sample>(){
            override fun areItemsTheSame(
                oldItem: Sample,
                newItem: Sample,
            ): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: Sample,
                newItem: Sample,
            ): Boolean {
                return oldItem==newItem
            }
        }
    }
}

