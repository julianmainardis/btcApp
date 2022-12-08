package com.example.bitcoinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.bitcoinapp.adapter.FiatPriceAdapter
import com.example.bitcoinapp.databinding.FragmentFiatPriceBinding
import com.example.bitcoinapp.viewmodel.FiatPriceViewModel
import com.example.domain.entity.Crypto
import org.koin.android.ext.android.inject

class FiatPriceFragment : Fragment() {

    private lateinit var binding: FragmentFiatPriceBinding
    private val viewModel: FiatPriceViewModel by inject()
    private val cryptoList: List<Crypto> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiatPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFiatPriceState().observe({ lifecycle }, ::updateUI)
        binding.searchButton.setOnClickListener { viewModel.getFiatPricePressed(binding.searchInputText.toString()) }
        setListener()
    }

    private fun updateUI(fiatPriceData: FiatPriceViewModel.FiatPriceData) {
        when (fiatPriceData.state) {
            FiatPriceViewModel.FiatPriceState.RESPONSE_SUCCESS -> showSelectedCrypto(fiatPriceData.data)
            FiatPriceViewModel.FiatPriceState.FILTERED -> showCryptoListFiltered(fiatPriceData.filteredData)
        }
    }

    private fun showSelectedCrypto(selectedCrypto: Crypto?) {
        binding.fiatPriceFragmentEmptyState.visibility = View.GONE
        binding.fiatPriceRecyclerView.visibility = View.GONE
        binding.apply {
            this.searchTextViewCurrency.text = selectedCrypto?.currency
            this.searchTextViewPrice.text = selectedCrypto?.actualValue.toString()
        }
    }

    private fun showCryptoListFiltered(cryptoFilteredList: List<Crypto>) {
        binding.fiatPriceFragmentEmptyState.visibility = View.GONE
        binding.fiatPriceRecyclerView.adapter = FiatPriceAdapter(cryptoList)
    }

    private fun setListener() {
        binding.searchInputText.addTextChangedListener {
            viewModel.filter(it.toString(), cryptoList)
        }
    }

    companion object {
        fun newInstance() = FiatPriceFragment()
    }
}
