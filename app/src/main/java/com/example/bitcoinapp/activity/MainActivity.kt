package com.example.bitcoinapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitcoinapp.databinding.ActivityMainBinding
import com.example.bitcoinapp.fragment.AllCryptoFragment
import com.example.bitcoinapp.fragment.FiatPriceFragment
import com.example.bitcoinapp.viewmodel.MainActivityViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainActivityViewModel by inject()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMainActivityState().observe({ lifecycle }, ::updateUI)
        binding.getAllButton.setOnClickListener { viewModel.getAllPressed() }
        binding.getFiatTypeButton.setOnClickListener { viewModel.getFiatPricePressed() }
    }

    private fun updateUI(mainActivityData: MainActivityViewModel.MainActivityData) {
        when (mainActivityData.state) {
            MainActivityViewModel.MainActivityState.GET_ALL -> AllCryptoFragment.newInstance()
            MainActivityViewModel.MainActivityState.GET_FIAT_TYPE -> FiatPriceFragment.newInstance()
        }
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}
