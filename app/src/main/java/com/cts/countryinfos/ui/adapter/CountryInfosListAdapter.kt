package com.cts.countryinfos.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cts.countryinfos.CountryInfoListActivity
import com.cts.countryinfos.R
import com.cts.countryinfos.databinding.CountryInfoListItemBinding
import com.cts.countryinfos.model.Info
import com.cts.countryinfos.ui.activity.CountryInfoListItemDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_info_list_item.view.*

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
        val info: Info = countryInfoList[position]
        if (info.title != null || info.description != null || info.imageHref != null) {
            Log.i(
                "Binding",
                "info.title  " + info.title + "info.description " + info.description + "info.imageHref " + info.imageHref
            )
            holder.bind(countryInfoList[position], context)
        }
    }


    class CountryInfoItemViewHolder(private val binding: CountryInfoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            info: Info,
            context: CountryInfoListActivity
        ) {
            with(binding) {

                binding.listItemDta = info
                Picasso.get()
                    .load(info.imageHref)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.root.ivInfoImage)

                binding.root.setOnClickListener {
                    val intent = CountryInfoListItemDetailActivity.newIntent(context, info)
                    context.startActivity(intent)


                }
                binding.executePendingBindings()


            }
        }


    }

    fun update(countryInfoList1: List<Info>) {
        countryInfoList = countryInfoList1
        notifyDataSetChanged()
    }
}