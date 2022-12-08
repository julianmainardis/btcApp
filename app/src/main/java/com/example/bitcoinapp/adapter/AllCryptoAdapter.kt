package com.example.bitcoinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.CardViewAllCryptoBinding
import com.example.domain.entity.Crypto

class AllCryptoAdapter(
    private val cryptos: List<Crypto>
) : RecyclerView.Adapter<AllCryptoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view_all_crypto,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cryptos[position])
    }

    override fun getItemCount(): Int = cryptos.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewAllCryptoBinding.bind(itemView)
        fun bind(item: Crypto) {
            binding.apply {
                this.cardViewAllCryptoCurrency.text = item.currency
                this.cardViewAllCryptoPrice.text = item.actualValue.toString()
            }
        }
    }
}
