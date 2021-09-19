package com.frank.coroutinespractice.multiplerequest.sequential

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class SequentialFragment: Fragment() {

    private lateinit var tvResult1: TextView
    private lateinit var tvResult2: TextView
    private lateinit var prbLoading: ProgressBar

    private val viewModel: SequentialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_sequential,null,false)

        tvResult1 = rootView.findViewById(R.id.tvResult1)
        tvResult2 = rootView.findViewById(R.id.tvResult2)
        prbLoading = rootView.findViewById(R.id.prbLoading)

        viewModel.data1.observe(viewLifecycleOwner,{
                data ->
            tvResult1.text = "Data: $data"
        })

        viewModel.data2.observe(viewLifecycleOwner,{
                data ->
            tvResult2.text = "Data: $data"
        })

        viewModel.isShowLoading.observe(viewLifecycleOwner,{
                isShow ->
            prbLoading.visibility = if(isShow) View.VISIBLE else View.GONE
        })
        return rootView
    }

}