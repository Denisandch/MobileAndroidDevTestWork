package com.ntpro.mobileandroiddevtestwork.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ntpro.mobileandroiddevtestwork.Deal
import com.ntpro.mobileandroiddevtestwork.R
import com.ntpro.mobileandroiddevtestwork.databinding.OneDealItemBinding
import java.text.SimpleDateFormat
import java.util.*

class DealAdapter(
    context: Context
) : PagingDataAdapter<Deal, DealViewHolder>(DealDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        return DealViewHolder(layoutInflater.inflate(R.layout.one_deal_item, parent, false))
    }

    override fun onBindViewHolder(holder: DealViewHolder, position: Int) {
        holder.bind(getItem(position = position))
    }
}

class DealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dealBinding = OneDealItemBinding.bind(itemView)

    fun bind(deal: Deal?) {
        deal?.let {
            with(dealBinding) {
                textItemName.text = deal.instrumentName
                textItemAmount.text = deal.amount.toString()
                textItemPrice.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        if (deal.side == Deal.Side.SELL) R.color.red else R.color.green
                    )
                )
                textItemPrice.text = deal.price.toString()
                val newFormat = SimpleDateFormat("EEE MMM dd yyyy\nHH:mm:ss", Locale.ENGLISH)
                textItemTime.text = newFormat.format(deal.timeStamp).toString()
            }
        }
    }
}

private object DealDiffItemCallback : DiffUtil.ItemCallback<Deal>() {
    override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
        return oldItem.id == newItem.id
    }

}