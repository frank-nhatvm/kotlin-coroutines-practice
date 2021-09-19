package com.frank.coroutinespractice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.frank.coroutinespractice.R
import com.frank.coroutinespractice.multiplerequest.cocurrency.ConcurrencyFragment
import com.frank.coroutinespractice.multiplerequest.sequential.SequentialFragment
import com.frank.coroutinespractice.noncancellable.NonCancellableFragment
import com.frank.coroutinespractice.retryfail.FailAndRetryFragment
import com.frank.coroutinespractice.singlerequest.SingleFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, null, false)

        rootView.findViewById<Button>(R.id.btnSingle).setOnClickListener {
            openScreen(SingleFragment())
        }

        rootView.findViewById<Button>(R.id.btnSequentital).setOnClickListener {
            openScreen(SequentialFragment())
        }

        rootView.findViewById<Button>(R.id.btnConcurency).setOnClickListener {
            openScreen(ConcurrencyFragment())
        }

        rootView.findViewById<Button>(R.id.btnFailAndRetry).setOnClickListener {
            openScreen(FailAndRetryFragment())
        }

        rootView.findViewById<Button>(R.id.btnNonCancellable).setOnClickListener {
            openScreen(NonCancellableFragment())
        }


        return rootView
    }

    private fun openScreen(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.name).commit()
    }

}