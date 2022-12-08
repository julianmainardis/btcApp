package com.example.bitcoinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bitcoinapp.R
import com.example.bitcoinapp.adapter.AllCryptoAdapter
import com.example.bitcoinapp.databinding.FragmentGetAllCryptoBinding
import com.example.bitcoinapp.viewmodel.AllCryptosViewModel
import com.example.domain.entity.Crypto
import org.koin.android.ext.android.inject

class AllCryptoFragment : Fragment() {

    private lateinit var binding: FragmentGetAllCryptoBinding
    private val viewModel: AllCryptosViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetAllCryptoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCryptoState().observe({ lifecycle }, ::updateUI)
        viewModel.getAllPressed()
    }

    private fun updateUI(cryptoData: AllCryptosViewModel.CryptoData) {
        when (cryptoData.state) {
            AllCryptosViewModel.CryptoState.RESPONSE_SUCCESS -> showCryptos(cryptoData.data)
            AllCryptosViewModel.CryptoState.RESPONSE_ERROR -> showError()
        }
    }

    private fun showCryptos(cryptoList: List<Crypto>) {
        binding.allCryptoFragmentEmptyState.visibility = View.GONE
        binding.allCryptoRecyclerView.adapter = AllCryptoAdapter(cryptoList)
    }

    private fun showError() {
        binding.allCryptoFragmentEmptyState.setText(R.string.empty_state_error)
    }

    companion object {
        fun newInstance() = AllCryptoFragment()
    }
}
