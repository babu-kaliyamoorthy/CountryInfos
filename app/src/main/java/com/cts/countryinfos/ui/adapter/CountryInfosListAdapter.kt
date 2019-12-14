package com.cts.countryinfos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cts.countryinfos.CountryInfoListActivity
import com.cts.countryinfos.databinding.CountryInfoListItemBinding
import com.cts.countryinfos.model.Info

/**
 * Created by Babu Kaliyamoorthy on 13/12/19.
 */
class CountryInfosListAdapter(
    private val context: CountryInfoListActivity,
    private var countryInfoList: List<Info>
) : RecyclerView.Adapter<CountryInfosListAdapter.CountryInfoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryInfoItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CountryInfoListItemBinding.inflate(inflater, parent, false)
        return CountryInfoItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryInfoList.size
    }

    override fun onBindViewHolder(holder: CountryInfoItemViewHolder, position: Int) {
        holder.bind(countryInfoList[position])
    }


    class CountryInfoItemViewHolder(private val binding: CountryInfoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Info) {
                with(binding) {

                    //binding.csvFileHeaders = IssueDetail.Headers
                binding.listItemDta = item
                binding.executePendingBindings()
            }
        }
    }

    fun update(countryInfoList1: List<Info>) {
        countryInfoList = countryInfoList1
        notifyDataSetChanged()
    }
}