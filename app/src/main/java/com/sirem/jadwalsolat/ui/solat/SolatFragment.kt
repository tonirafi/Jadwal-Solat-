package com.sirem.jadwalsolat.ui.solat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sirem.jadwalsolat.R

class SolatFragment : Fragment() {

    private lateinit var solatViewModel: SolatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        solatViewModel =
            ViewModelProviders.of(this).get(SolatViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_solat, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        solatViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}