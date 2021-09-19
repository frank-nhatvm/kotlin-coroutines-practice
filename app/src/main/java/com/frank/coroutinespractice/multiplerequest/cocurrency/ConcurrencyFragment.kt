package com.frank.coroutinespractice.multiplerequest.cocurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class ConcurrencyFragment : Fragment() {

    private val viewModel by viewModels<ConcurrencyViewModel>()

    private lateinit var prbLoadingBanner: ProgressBar
    private lateinit var prbLoadingProduct: ProgressBar
    private lateinit var prbLoadingAll: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_concurrency, null, false)

        prbLoadingAll = rootView.findViewById(R.id.prgLoadng)
        prbLoadingBanner = rootView.findViewById(R.id.prbLoadingBanner)
        prbLoadingProduct = rootView.findViewById(R.id.prbLoadingProduct)





        return rootView
    }
}