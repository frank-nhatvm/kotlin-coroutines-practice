package com.frank.coroutinespractice.retryfail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class FailAndRetryFragment : Fragment() {

    private lateinit var tvResult: TextView
    private lateinit var prbLoading: ProgressBar

    private val failAndRetryViewModel: FailAndRetryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        failAndRetryViewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fail_and_retry,null,false)

        tvResult = rootView.findViewById(R.id.tvResult)
        prbLoading = rootView.findViewById(R.id.prbLoading)

        failAndRetryViewModel.data.observe(viewLifecycleOwner,{
                data ->
            tvResult.text = "Data: $data"
        })

        failAndRetryViewModel.isShowLoading.observe(viewLifecycleOwner,{
                isShow ->
            prbLoading.visibility = if(isShow) View.VISIBLE else View.GONE
        })

        return rootView
    }

}