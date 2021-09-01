package com.frank.coroutinespractice.singlerequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.frank.coroutinespractice.R

class SingleFragment : Fragment() {

    private val viewModel by viewModels<SingleViewModel>()
    private lateinit var tvResult: TextView
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_single, null, false)

        tvResult = rootView.findViewById(R.id.tvResult)
        btnCancel = rootView.findViewById(R.id.btnCancel)

        viewModel.data.observe(viewLifecycleOwner,{
            data ->
            tvResult.text = "Result $data"
        })

        viewModel.error.observe(viewLifecycleOwner,{message ->
            tvResult.text = message
        })

        btnCancel.setOnClickListener {
            viewModel.cancel()
        }

        return rootView
    }

}