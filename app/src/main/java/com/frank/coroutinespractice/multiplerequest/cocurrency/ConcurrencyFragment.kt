package com.frank.coroutinespractice.multiplerequest.cocurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class ConcurrencyFragment : Fragment() {

    private lateinit var tvResult1: TextView
    private lateinit var tvResult2: TextView
    private lateinit var prbLoading: ProgressBar

    private val viewModel by viewModels<ConcurrencyViewModel>()


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

        tvResult1 = rootView.findViewById(R.id.tvResult1)
        tvResult2 = rootView.findViewById(R.id.tvResult2)
        prbLoading = rootView.findViewById(R.id.prbLoading)

        viewModel.listBanners.observe(viewLifecycleOwner,{
            listBanner ->
            tvResult1.text = "list banners: $listBanner"
        })

        viewModel.listLatestProducts.observe(viewLifecycleOwner,{
            listProducts ->
            tvResult2.text = "list product: $listProducts"
        })


        viewModel.isShowLoading.observe(viewLifecycleOwner,{
                isShow ->
            prbLoading.visibility = if(isShow) View.VISIBLE else View.GONE
        })



        return rootView
    }
}