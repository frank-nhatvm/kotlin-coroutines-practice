package com.frank.coroutinespractice.noncancellable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class NonCancellableFragment : Fragment() {

    private lateinit var tvResult: TextView
    private lateinit var prbLoading: ProgressBar

    private val viewModel: NonCancellableViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_noncancellable,null,false)

        tvResult = rootView.findViewById(R.id.tvResult)
        prbLoading = rootView.findViewById(R.id.prbLoading)

        viewModel.data.observe(viewLifecycleOwner,{
                data ->
            tvResult.text = "Data: $data"
        })

        viewModel.isShowLoading.observe(viewLifecycleOwner,{
                isShow ->
            prbLoading.visibility = if(isShow) View.VISIBLE else View.GONE
        })

        return rootView
    }

}