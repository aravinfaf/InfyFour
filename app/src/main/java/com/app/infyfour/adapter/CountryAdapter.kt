package com.app.infyfour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.infyfour.BR
import com.app.infyfour.R
import com.app.infyfour.data.model.CountryModel
import com.app.infyfour.databinding.AdapterLayoutBinding


class CountryAdapter(private val countrylist: ArrayList<CountryModel>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val binding: AdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CountryModel) {
            binding.setVariable(BR.country,data)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CountryViewHolder {
       val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding : AdapterLayoutBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.adapter_layout,parent,false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countrylist.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countrylist[position])
    }
}