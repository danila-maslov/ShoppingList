package com.danila.maslov.shoppinglist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danila.maslov.shoppinglist.R
import com.danila.maslov.shoppinglist.domain.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ShopListAdapter.ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke((shopItem))
        }
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvCount: TextView = view.findViewById(R.id.tv_count)
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}