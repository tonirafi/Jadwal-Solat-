package com.sirem.jadwalsolat.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sirem.jadwalsolat.R

class QuranFragment : Fragment() {

    private lateinit var quranViewModel: QuranViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quranViewModel =
            ViewModelProviders.of(this).get(QuranViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_quran, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        quranViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}