package com.example.apptez.view.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptez.R
import com.example.apptez.databinding.FragmentPharmacyBinding
import com.example.apptez.view.adapter.PharmacyAdapter
import com.example.apptez.view.viewModel.PharmacyViewModel

class FragmentPharmacy : Fragment() {
    private val pharmacyViewModel: PharmacyViewModel by viewModels()
    private lateinit var pharmacyAdapter:PharmacyAdapter
    private lateinit var binding:FragmentPharmacyBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_pharmacy,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pharmacyViewModel.getDataFromApi()

        observeData()
    }

    fun observeData(){
        pharmacyViewModel.pharmacyValue.observe(viewLifecycleOwner, Observer {
            it?.let {
                pharmacyAdapter=PharmacyAdapter()
                binding.adapter=pharmacyAdapter
                pharmacyAdapter.updateData(it)
            }

        })
    }




}