package com.ea.countryapp.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ea.countryapp.databinding.ItemListContentBinding
import com.ea.domain.model.country.CountryDO

class CountryListAdapter: ListAdapter<CountryDO,CountryListAdapter.CountryViewHolder>(CountryDiffUtils()) {

    var onItemClick: ((CountryDO)->(Unit))? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListContentBinding.inflate(inflater,parent,false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private class CountryDiffUtils: DiffUtil.ItemCallback<CountryDO>(){
        override fun areItemsTheSame(oldItem: CountryDO, newItem: CountryDO): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CountryDO, newItem: CountryDO): Boolean {
            return oldItem == newItem
        }

    }

    inner class CountryViewHolder(private val binding: ItemListContentBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(countryDO: CountryDO){
            binding.content.text = countryDO.name
            binding.root.setOnClickListener {
                onItemClick?.invoke(countryDO)
            }
        }
    }
}
